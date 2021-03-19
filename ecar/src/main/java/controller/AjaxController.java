package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class AjaxController {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		List<Map<String, String>> listmap = new ArrayList<Map<String,String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = new HashMap<String, String>();
        try {
            FileInputStream file = new FileInputStream("C:/Users/sa365/Desktop/ecarlist.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            int rowindex=0;
            int columnindex=0;
            //시트 수 (첫번째에만 존재하므로 0을 준다)
            //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
            XSSFSheet sheet=workbook.getSheetAt(0);
            //행의 수
            int rows=sheet.getPhysicalNumberOfRows();
            for(rowindex=0;rowindex<rows;rowindex++){
                //행을읽는다
                XSSFRow row=sheet.getRow(rowindex);
                if(row !=null){
                    //셀의 수
                    int cells=row.getPhysicalNumberOfCells();
                    for(columnindex=0; columnindex<=cells; columnindex++){
                        //셀값을 읽는다
                        XSSFCell cell=row.getCell(columnindex);
                        String value="";
                        //셀이 빈값일경우를 위한 널체크
                        if(cell==null){
                            continue;
                        }else{
                            //타입별로 내용 읽기
                            switch (cell.getCellType()){
                            case XSSFCell.CELL_TYPE_FORMULA:
                                value=cell.getCellFormula();
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                value=cell.getNumericCellValue()+"";
                                break;
                            case XSSFCell.CELL_TYPE_STRING:
                                value=cell.getStringCellValue()+"";
                                break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                value=cell.getBooleanCellValue()+"";
                                break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                value=cell.getErrorCellValue()+"";
                                break;
                            }
                        }
                        //System.out.println(rowindex+"번 행 : "+columnindex+"번 열 값은: "+value);
                        if(rowindex==0) {
                        	list.add(value);
                        }
                        if(rowindex==1 ){
                        	map1.put(list.get(columnindex), value);
                        }
                        if(rowindex==2) {
                        	map2.put(list.get(columnindex), value);
                        }
                        if(rowindex==3){
                        	map3.put(list.get(columnindex), value);
                        }
                        
                    }
 
                }
            }
            listmap.add(map1);
            listmap.add(map2);
            listmap.add(map3);
            list1.addAll(listmap.get(0).keySet());
            System.out.println(listmap);
            for(int j=0; j<list1.size();j++) {
            	for(int i=0;i<listmap.size();i++) {
            		System.out.println(listmap.get(i).get(list1.get(j)));
            	}
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
 
    }
}
