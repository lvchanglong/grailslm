package common

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * Created by lvchanglong on 2017/10/20.
 */
class ExcelHelper {

    /**
     * 获得文件类型
     * @param file
     * @return
     */
    static String getFileType(String fileName) {
        def splits = fileName.split(/\./)
        return splits[splits.length - 1].toLowerCase()
    }

    /**
     * xls || xlsx
     * @param file
     * @param nFirstRowIdx 0
     * @param nLastRowIdx 3
     * @param nFirstCellIdx 0
     * @param nLastCellIdx 1
     * @param unique
     * @return
     */
    static def readExcel(File file, def nFirstRowIdx, def nLastRowIdx, def nFirstCellIdx, def nLastCellIdx, boolean unique = false) {
        def fileName = file.getName()
        def fileType = this.getFileType(fileName)

        switch (fileType.toLowerCase()) {
            case "xls":
                return this.readXls(file, nFirstRowIdx, nLastRowIdx, nFirstCellIdx, nLastCellIdx, unique)
                break
            case "xlsx":
                return this.readXlsx(file, nFirstRowIdx, nLastRowIdx, nFirstCellIdx, nLastCellIdx, unique)
                break
        }
        return null
    }

    /**
     * xls
     * @param file
     * @param nFirstCellIdx
     * @param nLastCellIdx
     */
    static def readXls(File file, def nFirstRowIdx, def nLastRowIdx, def nFirstCellIdx, def nLastCellIdx, boolean unique = false) {
        def inputStream = file.newInputStream()
        def workbook = new HSSFWorkbook(inputStream)
        return this.getSheets(workbook, nFirstRowIdx, nLastRowIdx, nFirstCellIdx, nLastCellIdx, unique)
    }

    /**
     * xlsx
     * @param file
     * @param nFirstCellIdx
     * @param nLastCellIdx
     */
    static def readXlsx(File file, def nFirstRowIdx, def nLastRowIdx, def nFirstCellIdx, def nLastCellIdx, boolean unique = false) {
        def inputStream = file.newInputStream()
        def workbook = new XSSFWorkbook(inputStream)
        return this.getSheets(workbook, nFirstRowIdx, nLastRowIdx, nFirstCellIdx, nLastCellIdx, unique)
    }

    /**
     * xls | xlsx
     */
    static void writeExcel(File file, def array) {
        def fileName = file.getName()
        def fileType = this.getFileType(fileName)
        def workbook = null
        switch (fileType.toLowerCase()) {
            case "xls":
                workbook = new HSSFWorkbook()
                break
            case "xlsx":
                workbook = new XSSFWorkbook()
                break
        }
        def sheet = workbook.createSheet("sheet1")
        array.eachWithIndex { hm, i->
            def row = sheet.createRow(i)
            hm.each { k, v->
                def cell = row.createCell(k, CellType.STRING)
                cell.setCellValue(v.toString())
            }
        }
        workbook.write(file)
    }

    /**
     * 读取数据（合并为一个集合）
     * @param workbook
     * @param nFirstCellIdx
     * @param nLastCellIdx
     * @param unique
     * @return
     */
    static def getSheets(def workbook, def nFirstRowIdx, def nLastRowIdx, def nFirstCellIdx, def nLastCellIdx, boolean unique = false) {
        def arraySheet = new ArrayList()
        if(unique) {
            arraySheet = new HashSet()
        }

        for (int i=0;i<workbook.getNumberOfSheets();i++) { //表
            Sheet sheet = workbook.getSheetAt(i)
            if(sheet) {
                String sheetName = sheet.getSheetName()
//              println "sheetName:"+sheet.getSheetName()
                def firstRowIdx = sheet.getFirstRowNum()
                def lastRowIdx = sheet.getLastRowNum()

                if(nFirstRowIdx != null && nLastRowIdx != null) { //重新赋值
                    firstRowIdx = nFirstRowIdx
                    lastRowIdx	= nLastRowIdx
                }

                ArrayList arrayRow = new ArrayList() //rows
                for(int j=firstRowIdx;j<=lastRowIdx;j++) { //行
                    def row = sheet.getRow(j)
                    if(row != null) {
                        def firstCellIdx = row.getFirstCellNum()
                        def lastCellIdx =  row.getLastCellNum()

                        if(nFirstCellIdx != null && nLastCellIdx != null) { //重新赋值
                            firstCellIdx = nFirstCellIdx
                            lastCellIdx	= nLastCellIdx
                        }

                        ArrayList arrayCell = new ArrayList() //cells
                        for(int z=firstCellIdx;z<=lastCellIdx;z++) { //列
                            def cell = row.getCell(z)
                            if(cell != null) {
                                //cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING)
                                println cell.getCellType()
                                def cellValue = ExcelHelper.getValue(cell)
                                arrayCell.add(cellValue)
                            } else {
                                arrayCell.add(null)
                            }
                        }
                        if(!arrayCell.isEmpty()) {
                            arrayRow.add(arrayCell)
                        }
                    }
                }//row

                if(!arrayRow.isEmpty()) {
                    arraySheet.addAll(arrayRow)
                }
            }
        }//sheet

        return arraySheet
    }

    static def getValue(def cell) {
        def data = ""
        try{
            Integer cellType = cell.getCellType()
            switch(cellType) {
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC :
                    // 数字
                    data = cell.getNumericCellValue() + ""
                    if(-1 != data.lastIndexOf(".0")) {
                        data = cell.getNumericCellValue().toDouble().toInteger() + ""
                    }
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING :
                    // 字串
                    data = cell.getStringCellValue()
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA :
                    // 公式
                    data = cell.getNumericCellValue() + ""
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK :
                    // 空白
                    data = ""
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN :
                    // 步林
                    data = cell.getBooleanCellValue() + ""
                    break;
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR :
                    // Error
                    data = ""
                    break;
            }
        } catch( Exception err2 ) {
            data = ""
        }
        return data
    }

    public static void main(String[] args ) {
//        def file = common.FileMng.getFile("D:\\", "result-ENG.xls")
//        def rows = common.ExcelHelper.readExcel(file, 0, 30, 0, 3)
//        println rows
//        Workbook workbook = new Workbook(file.newInputStream())
//        WorksheetCollection worksheetCollection = workbook.getWorksheets()
//        worksheetCollection.each { Worksheet sheet->
//            Cells cells = sheet.getCells()
//            for(int r=cells.getMinDataRow(); r<=cells.getMaxDataRow(); r++) {
//                Cell abs = cells.get(r, 0)
//                Cell src = cells.get(r, 1)
//                Cell tar = cells.get(r, 2)
//                println abs.getValue() + "-" + src.getValue() + "-" + tar.getValue()
//            }
//        }

//        Workbook workbook = new Workbook(file.newInputStream())
//        workbook.getWorksheets().each {Worksheet sheet->
//            Cells cells = sheet.getCells()
//            for(int r=cells.getMinRow(); r<=cells.getMaxDataRow(); r++) {
//                Cell abs = cells.get(r, 0)
//                Cell src = cells.get(r, 1)
//                Cell tar = cells.get(r, 2)
//                println abs.getStringValue() + "-" + src.getStringValue() + "-" + tar.getStringValue()
//            }
//        }
    }
}
