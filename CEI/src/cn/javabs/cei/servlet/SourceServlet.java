package cn.javabs.cei.servlet;

import cn.javabs.cei.entity.Source;
import cn.javabs.cei.service.SourceService;
import cn.javabs.cei.service.serviceimpl.SourceServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@WebServlet( "/sourceServlet")
@MultipartConfig(maxFileSize = 1024*500)//最大值 必须设置
public class SourceServlet extends HttpServlet {
    SourceService sourceService = new SourceServiceImpl();
    Source source=new Source();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet (request,  response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String op=  request.getParameter("op");
      if("addSource".equals(op)){
          addSource(request,response);
      }else if("updateSource".equals(op)){
          updateSource(request,response);
      }else if("delSource".equals(op)){
          delSource( request,  response);
      }else if("findAllSources".equals(op)){
          findAllSources( request,  response);
      }else if("editSource".equals(op)){
          editSource( request,  response);
      }else if("goToAddSourceView".equals(op)){
          goToAddSourceView( request,  response);
      }else if("downloadSource".equals(op)){
          downloadSource( request,  response);
      }
      else{
          System.out.println("参数传递有误！");
      }
    }
    private void downloadSource(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        int sourceId = Integer.parseInt(id);
         source=sourceService.findSourceById(sourceId);
        //如果有资源，就可以下载
        if (source != null){
            // 获取资源文件的路径和名称
            String path = source.getPath();
            String name = source.getName();
//            String absou = request.getContextPath();
            String fileName = path +name;

           System.out.println("fileName"+ fileName);//新加
            String realPath = this.getServletContext().getRealPath("/upload/");
            File file = new File(fileName);
            if ( file!=null){
                try {
                    FileInputStream fileInputStream = new FileInputStream(fileName);
                   String filename = URLEncoder.encode(file.getName(), "utf-8");
                    byte[] b = new byte[fileInputStream.available()];
                    fileInputStream.read(b);
                    response.setCharacterEncoding("utf-8");
                    response.setHeader("Content-Disposition","attachment; filename="+filename+"");
                    //获取响应报文输出流对象
                    ServletOutputStream out =response.getOutputStream();
                    //输出
                    out.write(b);
                    out.flush();
                    out.close();

                } catch (Exception e) {
                    throw  new RuntimeException(e);
                }
            }
        }


    }








    private void goToAddSourceView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/addSource.jsp").forward(request,response);
    }


    private void findAllSources(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Source> list = sourceService.findAllSource();

        request.setAttribute("list",list);
       request.getRequestDispatcher("/admin/SourceList.jsp").forward(request,response);
    }

    private void delSource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int  sourceId = Integer.parseInt(id);
        int rows=sourceService.delSource(sourceId);
        if (rows>0){
            request.setAttribute("msg","删除资源成功");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","删除资源失败");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }

    }

    private void updateSource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int  sourceId = Integer.parseInt(id);
        Source source=new Source();
        try {
            source.setId(sourceId);
            BeanUtils.populate(source,request.getParameterMap());
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

        int rows=sourceService.updateSource(source);
        if (rows>0){
            request.setAttribute("msg","修改资源成功");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","修改资源失败");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }
    }

    private void addSource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


       String path=this.getServletContext().getRealPath("upload/");
        System.out.println("path:"+path); //打印是在idea后台打印出来的 如果打印出来了表示在代码运行成功

        File mainDir = new File(path);//创建多级目录
        if (!mainDir.exists()){
            mainDir.mkdirs();
       }
        Part part=request.getPart("name");//part获取文件
         String fileName=part.getSubmittedFileName();

         if (!fileName.equals("") || fileName != null){
         String name = UUID.randomUUID().toString();
         System.out.println("name:"+name);
           if (fileName.contains(".")){
               int index = fileName.indexOf(".");
              String newName = fileName.substring(index);
               System.out.println("newName:"+newName);
              fileName =  name +newName;
          }
       }
        System.out.println("fileName:" + fileName);

        part.write(path+"/"+fileName);

        source.setName(fileName);
        source.setPath(path);


        try {
            BeanUtils.populate(source,request.getParameterMap());
            int rows=sourceService.addSource(source);
            System.out.println("source:"+source); //新加的
            if(rows>0){
                request.setAttribute("msg","添加资源成功");
                request.getRequestDispatcher("/message.jsp").forward(request,response);
            }else{
                request.setAttribute("msg","添加资源失败");
                request.getRequestDispatcher("/message.jsp").forward(request,response);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    private void editSource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("id:"+id);
        int  sourceId = Integer.parseInt(id);
        System.out.println("sourceId:"+sourceId);
        Source source=sourceService.findSourceById(sourceId);
        if (source != null){
            request.setAttribute("source",source);
            request.getRequestDispatcher("/admin/updateSource.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","资源数据回显失败");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }
    }
}
