package common

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Created by lvchanglong on 17-2-24.
 */
class PdfBox {

    File[] pdf2imgs(File pdf, String imgType = "png") {
        def root = new File("${pdf.getAbsolutePath()}_temp") //与文件同名的文件夹
        if(!root.exists()) {
            root.mkdirs()
        }
        String fileName = pdf.getName()
        int endIndex = fileName.lastIndexOf(".")
        String name = fileName.substring(0, endIndex)

        PDDocument document = PDDocument.load(pdf)
        PDFRenderer renderer = new PDFRenderer(document)
        def pageCount = document.getNumberOfPages()
        for(int idx in 0..pageCount-1) {
            BufferedImage bufferedImage = renderer.renderImage(idx, 2.5f)
            ImageIO.write(bufferedImage, imgType, new File(root.getAbsolutePath(), "${name}_${idx + 1}.${imgType}"))
        }
        document.close()
        return root.listFiles()
    }

    static main(args) {
        def pdfBox = new PdfBox()
        pdfBox.pdf2imgs(new File("D:\\down", "test.pdf"))
    }

}
