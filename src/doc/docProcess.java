package doc;

import config.Image;
import config.Config;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.org.apache.poi.util.IOUtils;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.*;
import util.SaveImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

public class docProcess {

    public boolean isCreateDoc() {
        return isCreateDoc;
    }

    private boolean isCreateDoc;
    public docProcess() {
        isCreateDoc = false;
    }


    public void createDoc(Config config) throws FileNotFoundException {
        try {
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
            MainDocumentPart wordDocumentPart = wordMLPackage.getMainDocumentPart();
            wordDocumentPart.addParagraphOfText("Версия ФП:");
            wordDocumentPart.addParagraphOfText("Выводы по тесту:");

            InputStream inputStream;

            String filenameHint = "filenameHint";
            String altText = "altText";
            int i = 1;

            if (config.getImages() != null) {
                for (Image image : config.getImages()) {
                    System.out.println(Config.getImageUrl(config, image));
                    wordDocumentPart.addParagraphOfText("Рисунок " + i++ + ". " + image.getName() + " " + image.getServerName() + ".");

                    inputStream = new SaveImage(Config.getImageUrl(config, image),
                            image.getServerName()).GetImageInputStream();
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    inputStream.close();

                    P p = newImage(wordMLPackage, bytes, filenameHint, altText, 0, 1);
                    wordDocumentPart.addObject(p);

                }
                String hr = "ФП." + config.getSystemName() + " " + config.getTestName()+" от "
                        + config.getStartDate().substring(0, 10);

                createHeaderPart(wordMLPackage, hr);
                addFooterToDocument(wordMLPackage, "1.0");
            }
            wordMLPackage.save(new File(config.getFileFolder() + config.getFileName()));

        } catch ( Exception e) {
            e.printStackTrace();
            return;
        }
        isCreateDoc = true;
    }
    public static P newImage(WordprocessingMLPackage wordMLPackage, byte[] bytes,
                             String filenameHint, String altText, int id1, int id2) throws Exception {
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);
        Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2);

        ObjectFactory factory = new ObjectFactory();

        P p = factory.createP();
        R run = factory.createR();

        p.getParagraphContent().add(run);
        Drawing drawing = factory.createDrawing();
        run.getRunContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);

        return p;
    }

    private static void addFooterToDocument(WordprocessingMLPackage wordMLPackage,
                                            String docVersionNumber) throws InvalidFormatException {
        ObjectFactory factory = Context.getWmlObjectFactory();
        Relationship relationship = createFooterPart(wordMLPackage, factory,
                docVersionNumber);
        createFooterReference(relationship, wordMLPackage, factory);
    }
    private static Relationship createFooterPart(
            WordprocessingMLPackage wordMLPackage, ObjectFactory factory,
            String docversionNumber) throws InvalidFormatException {
        FooterPart footerPart = new FooterPart();
        footerPart.setPackage(wordMLPackage);
        StringBuilder footerText = new StringBuilder("");
        String documentNumber[] = docversionNumber.split("\\.");
        footerText.append(documentNumber[0]);
        footerText.append("v.");
        footerText.append(documentNumber[1]);
        footerPart.setJaxbElement(createFooter(footerText.toString(), factory));
        return wordMLPackage.getMainDocumentPart().addTargetPart(footerPart);
    }
    private static Ftr createFooter(String content, ObjectFactory factory) {
        Ftr footer = factory.createFtr();
        P paragraph = factory.createP();
        R run = factory.createR();
/*
      * Change the font size to 8 points(the font size is defined to be in
      * half-point size so set the value as 16).
*/
        RPr rpr = new RPr();
        HpsMeasure size = new HpsMeasure();
        size.setVal(BigInteger.valueOf(16));
        rpr.setSz(size);
        run.setRPr(rpr);
        Text text = new Text();
        text.setValue(content);
        run.getContent().add(text);
        paragraph.getContent().add(run);
        footer.getContent().add(paragraph);
        P pageNumParagraph = factory.createP();
        addFieldBegin(factory, pageNumParagraph);
        addPageNumberField(factory, pageNumParagraph);
        addFieldEnd(factory, pageNumParagraph);
        footer.getContent().add(pageNumParagraph);
        return footer;
    }

    private static void addPageNumberField(ObjectFactory factory, P paragraph) {
        R run = factory.createR();
        PPr ppr = new PPr();
        Jc jc = new Jc();
        jc.setVal(JcEnumeration.CENTER);
        ppr.setJc(jc);
        paragraph.setPPr(ppr);
        Text txt = new Text();
        txt.setSpace("preserve");
        txt.setValue(" PAGE   \\* MERGEFORMAT ");
        run.getContent().add(factory.createRInstrText(txt));
        paragraph.getContent().add(run);
    }
    private static void createFooterReference(Relationship relationship,
                                              WordprocessingMLPackage wordMLPackage, ObjectFactory factory) {
        List<SectionWrapper> sections = wordMLPackage.getDocumentModel()
                .getSections();
        SectPr sectionProperties = sections.get(sections.size() - 1)
                .getSectPr();
        // There is always a section wrapper, but it might not contain a sectPr
        if (sectionProperties == null) {
            sectionProperties = factory.createSectPr();
            wordMLPackage.getMainDocumentPart().addObject(sectionProperties);
            sections.get(sections.size() - 1).setSectPr(sectionProperties);
        }


}
    private static void addFieldBegin(ObjectFactory factory, P paragraph) {
        R run = factory.createR();
        FldChar fldchar = factory.createFldChar();
        fldchar.setFldCharType(STFldCharType.BEGIN);
        run.getContent().add(fldchar);
        paragraph.getContent().add(run);
    }

    private static void addFieldEnd(ObjectFactory factory, P paragraph) {
        FldChar fldcharend = factory.createFldChar();
        fldcharend.setFldCharType(STFldCharType.END);
        R run3 = factory.createR();
        run3.getContent().add(fldcharend);
        paragraph.getContent().add(run3);
    }
    private static void createHeaderPart(WordprocessingMLPackage wordMLPackage, String hr)
            throws InvalidFormatException {
        ObjectFactory factory = Context.getWmlObjectFactory();
        HeaderPart headerPart = new HeaderPart();
        headerPart.setPackage(wordMLPackage);

        Hdr header = factory.createHdr();
        P paragraph = factory.createP();
        R run = factory.createR();
        //* Change the font size to 8 points(the font size is defined to be in
        //* half-point size so set the value as 16).

        RPr rpr = new RPr();
        HpsMeasure size = new HpsMeasure();
        size.setVal(BigInteger.valueOf(18));
        rpr.setSz(size);
        run.setRPr(rpr);
        Text text = new Text();
        text.setValue(hr);
        run.getContent().add(text);
        paragraph.getContent().add(run);
        header.getContent().add(paragraph);
        headerPart.setJaxbElement(header);

        Relationship relationship = wordMLPackage.getMainDocumentPart()
                .addTargetPart(headerPart);
        List<SectionWrapper> sections = wordMLPackage.getDocumentModel()
                .getSections();
        SectPr sectionProperties = sections.get(sections.size() - 1)
                .getSectPr();
        // There is always a section wrapper, but it might not contain a sectPr
        if (sectionProperties == null) {
            sectionProperties = factory.createSectPr();
            wordMLPackage.getMainDocumentPart().addObject(sectionProperties);
            sections.get(sections.size() - 1).setSectPr(sectionProperties);
        }

//      * Remove Header if it already exists.

        List<CTRel> relations = sectionProperties.getEGHdrFtrReferences();
        Iterator<CTRel> relationsItr = relations.iterator();
        while (relationsItr.hasNext()) {
            CTRel relation = relationsItr.next();
            if (relation instanceof HeaderReference) {
                relationsItr.remove();
            }
        }

        HeaderReference headerReference = factory.createHeaderReference();
        headerReference.setId(relationship.getId());
        headerReference.setType(HdrFtrRef.DEFAULT);
        sectionProperties.getEGHdrFtrReferences().add(headerReference);
        HeaderReference firstPageHeaderRef = factory.createHeaderReference();
        firstPageHeaderRef.setId(relationship.getId());
        firstPageHeaderRef.setType(HdrFtrRef.FIRST);
        sectionProperties.getEGHdrFtrReferences().add(firstPageHeaderRef);
    }
}
