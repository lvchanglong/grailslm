package common

class AsposeLicense {

    public static boolean setLicense() {
        try {
            String xml = '<License>\n' +
                    '  <Data>\n' +
                    '    <Products>\n' +
                    '      <Product>Aspose.Total for Java</Product>\n' +
                    '      <Product>Aspose.Total for Java</Product>\n' +
                    '    </Products>\n' +
                    '    <EditionType>Enterprise</EditionType>\n' +
                    '    <SubscriptionExpiry>20991231</SubscriptionExpiry>\n' +
                    '    <LicenseExpiry>20991231</LicenseExpiry>\n' +
                    '    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n' +
                    '  </Data>\n' +
                    '  <Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n' +
                    '</License>'
            def bytes = xml.getBytes()

            def wordsLiscense = new com.aspose.words.License()
            wordsLiscense.setLicense(new ByteArrayInputStream(bytes))

            def cellsLiscense = new com.aspose.cells.License()
            cellsLiscense.setLicense(new ByteArrayInputStream(bytes))

            def slidesLiscense = new com.aspose.slides.License()
            slidesLiscense.setLicense(new ByteArrayInputStream(bytes))

            def pdfLiscense = new com.aspose.pdf.License()
            pdfLiscense.setLicense(new ByteArrayInputStream(bytes))
            return true
        } catch (Exception e) {
            e.printStackTrace()
        }
        return false
    }
    
}
