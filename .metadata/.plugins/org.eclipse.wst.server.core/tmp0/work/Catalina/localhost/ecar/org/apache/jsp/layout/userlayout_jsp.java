/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.39
 * Generated at: 2021-03-05 03:19:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userlayout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<title>E-car</title>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Lato\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n");
      out.write("<style>\r\n");
      out.write("html,body,h1,h2,h3,h4 {font-family:\"Lato\", sans-serif}\r\n");
      out.write(".w3-top {\r\n");
      out.write("    font-family: Verdana,sans-serif;\r\n");
      out.write("    font-size: 20px;\r\n");
      out.write("    line-height: 1.5;\r\n");
      out.write("}\r\n");
      out.write(".mySlides {display:none}\r\n");
      out.write(".w3-tag, .fa {cursor:pointer}\r\n");
      out.write(".w3-tag {height:15px;width:15px;padding:0;margin-top:6px}\r\n");
      out.write("</style>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<!-- Navbar (sit on top) -->\r\n");
      out.write("<div class=\"w3-top\">\r\n");
      out.write("  <div class=\"w3-bar w3-white w3-card\" id=\"myNavbar\">\r\n");
      out.write("    <a href=\"#home\" class=\"w3-bar-item w3-button w3-wide\">E-car</a>\r\n");
      out.write("    <!-- Right-sided navbar links -->\r\n");
      out.write("    <div class=\"w3-right w3-hide-small\">\r\n");
      out.write("      <a href=\"/user/main.shop\" class=\"w3-bar-item w3-button\">전기차소개</a>\r\n");
      out.write("      <a href=\"#oil\" class=\"w3-bar-item w3-button\"> 전기차충전소조회</a>\r\n");
      out.write("      <a href=\"#kind\" class=\"w3-bar-item w3-button\"> 전기차종류</a>\r\n");
      out.write("      <a href=\"#pricing\" class=\"w3-bar-item w3-button\"> 전기차지원금</a>\r\n");
      out.write("      <a href=\"#news\" class=\"w3-bar-item w3-button\"> 뉴스</a>\r\n");
      out.write("      <a href=\"#board\" class=\"w3-bar-item w3-button\"> 게시판</a>\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- Hide right-floated links on small screens and replace them with a menu icon -->\r\n");
      out.write("\r\n");
      out.write("    <a href=\"javascript:void(0)\" class=\"w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium\" onclick=\"w3_open()\">\r\n");
      out.write("      <i class=\"fa fa-bars\"></i>\r\n");
      out.write("    </a>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!-- Content -->\r\n");
      out.write("<div class=\"w3-content\" style=\"max-width:1100px;margin-top:80px;margin-bottom:80px\">\r\n");
      out.write("\r\n");
      out.write("<!--   <div class=\"w3-panel\">\r\n");
      out.write("    <h1><b>MARKETING</b></h1>\r\n");
      out.write("    <p>Template by w3.css</p>\r\n");
      out.write("  </div> -->\r\n");
      out.write("\r\n");
      out.write("  <!-- Slideshow -->\r\n");
      out.write("  <div class=\"w3-container\">\r\n");
      out.write("    <div class=\"w3-display-container mySlides\">\r\n");
      out.write("      <img src=\"ecar.png\" style=\"width:100%\">\r\n");
      out.write("      <div class=\"w3-display-topleft w3-container w3-padding-32\">\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"w3-display-container mySlides\">\r\n");
      out.write("      <img src=\"whitecar.PNG\" style=\"width:100%\">\r\n");
      out.write("      <div class=\"w3-display-middle w3-container w3-padding-32\">\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"w3-display-container mySlides\">\r\n");
      out.write("      <img src=\"bluecar.png\" style=\"width:100%\">\r\n");
      out.write("      <div class=\"w3-display-topright w3-container w3-padding-32\">\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <!-- Slideshow next/previous buttons -->\r\n");
      out.write("    <div class=\"w3-container w3-dark-grey w3-padding w3-xlarge\">\r\n");
      out.write("      <div class=\"w3-left\" onclick=\"plusDivs(-1)\"><i class=\"fa fa-arrow-circle-left w3-hover-text-teal\"></i></div>\r\n");
      out.write("      <div class=\"w3-right\" onclick=\"plusDivs(1)\"><i class=\"fa fa-arrow-circle-right w3-hover-text-teal\"></i></div>\r\n");
      out.write("    \r\n");
      out.write("      <div class=\"w3-center\">\r\n");
      out.write("        <span class=\"w3-tag demodots w3-border w3-transparent w3-hover-white\" onclick=\"currentDiv(1)\"></span>\r\n");
      out.write("        <span class=\"w3-tag demodots w3-border w3-transparent w3-hover-white\" onclick=\"currentDiv(2)\"></span>\r\n");
      out.write("        <span class=\"w3-tag demodots w3-border w3-transparent w3-hover-white\" onclick=\"currentDiv(3)\"></span>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("  \r\n");
      out.write("  <!-- Grid -->\r\n");
      out.write("  <div class=\"w3-row w3-container\">\r\n");
      out.write("    <div class=\"w3-center w3-padding-64\">\r\n");
      out.write("      <span class=\"w3-xlarge w3-bottombar w3-border-dark-grey w3-padding-16\">What We Offer</span>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"w3-col l3 m6 w3-light-grey w3-container w3-padding-16\">\r\n");
      out.write("      <h3>news</h3>\r\n");
      out.write("      <p>테슬라 짱</p>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"w3-col l3 m6 w3-grey w3-container w3-padding-16\">\r\n");
      out.write("      <h3>graph</h3>\r\n");
      out.write("      <p>전기자동차 이용 현황 그래프. 증가합니다~</p>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("  <!-- Grid -->\r\n");
      out.write("  <div class=\"w3-row-padding\" id=\"about\">\r\n");
      out.write("    <div class=\"w3-center w3-padding-64\">\r\n");
      out.write("      <span class=\"w3-xlarge w3-bottombar w3-border-dark-grey w3-padding-16\">Who We Are</span>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"w3-third w3-margin-bottom\">\r\n");
      out.write("      <div class=\"w3-card-4\">\r\n");
      out.write("        <img src=\"/w3images/team1.jpg\" alt=\"John\" style=\"width:50%\">\r\n");
      out.write("        <div class=\"w3-container\">\r\n");
      out.write("          <h3>남현식</h3>\r\n");
      out.write("          <p class=\"w3-opacity\">조장</p>\r\n");
      out.write("          <p></p>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"w3-third w3-margin-bottom\">\r\n");
      out.write("      <div class=\"w3-card-4\">\r\n");
      out.write("        <img src=\"/w3images/team2.jpg\" alt=\"Mike\" style=\"width:50%\">\r\n");
      out.write("        <div class=\"w3-container\">\r\n");
      out.write("          <h3>이나영</h3>\r\n");
      out.write("          <p class=\"w3-opacity\">조원</p>\r\n");
      out.write("          <p></p>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"w3-third w3-margin-bottom\">\r\n");
      out.write("      <div class=\"w3-card-4\">\r\n");
      out.write("        <img src=\"/w3images/team2.jpg\" alt=\"Mike\" style=\"width:50%\">\r\n");
      out.write("        <div class=\"w3-container\">\r\n");
      out.write("          <h3>임하영</h3>\r\n");
      out.write("          <p class=\"w3-opacity\">조원</p>\r\n");
      out.write("          <p></p>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("        <div class=\"w3-third w3-margin-bottom\">\r\n");
      out.write("      <div class=\"w3-card-4\">\r\n");
      out.write("        <img src=\"/w3images/team2.jpg\" alt=\"Mike\" style=\"width:50%\">\r\n");
      out.write("        <div class=\"w3-container\">\r\n");
      out.write("          <h3>박송은</h3>\r\n");
      out.write("          <p class=\"w3-opacity\">조원</p>\r\n");
      out.write("          <p></p>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Footer -->\r\n");
      out.write("\r\n");
      out.write("<footer class=\"w3-container w3-padding-32 w3-light-grey w3-center\">\r\n");
      out.write("  <h4>Footer</h4>\r\n");
      out.write("  <a href=\"#\" class=\"w3-button w3-black w3-margin\"><i class=\"fa fa-arrow-up w3-margin-right\"></i>To the top</a>\r\n");
      out.write("  <div class=\"w3-xlarge w3-section\">\r\n");
      out.write("    <i class=\"fa fa-facebook-official w3-hover-opacity\"></i>\r\n");
      out.write("    <i class=\"fa fa-instagram w3-hover-opacity\"></i>\r\n");
      out.write("    <i class=\"fa fa-snapchat w3-hover-opacity\"></i>\r\n");
      out.write("    <i class=\"fa fa-pinterest-p w3-hover-opacity\"></i>\r\n");
      out.write("    <i class=\"fa fa-twitter w3-hover-opacity\"></i>\r\n");
      out.write("    <i class=\"fa fa-linkedin w3-hover-opacity\"></i>\r\n");
      out.write("  </div>\r\n");
      out.write("  <p>Powered by <a href=\"https://www.w3schools.com/w3css/default.asp\" title=\"W3.CSS\" target=\"_blank\" class=\"w3-hover-text-green\">w3.css</a></p>\r\n");
      out.write("</footer>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("// Slideshow\r\n");
      out.write("var slideIndex = 1;\r\n");
      out.write("showDivs(slideIndex);\r\n");
      out.write("\r\n");
      out.write("function plusDivs(n) {\r\n");
      out.write("  showDivs(slideIndex += n);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function currentDiv(n) {\r\n");
      out.write("  showDivs(slideIndex = n);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function showDivs(n) {\r\n");
      out.write("  var i;\r\n");
      out.write("  var x = document.getElementsByClassName(\"mySlides\");\r\n");
      out.write("  var dots = document.getElementsByClassName(\"demodots\");\r\n");
      out.write("  if (n > x.length) {slideIndex = 1}    \r\n");
      out.write("  if (n < 1) {slideIndex = x.length} ;\r\n");
      out.write("  for (i = 0; i < x.length; i++) {\r\n");
      out.write("    x[i].style.display = \"none\";  \r\n");
      out.write("  }\r\n");
      out.write("  for (i = 0; i < dots.length; i++) {\r\n");
      out.write("    dots[i].className = dots[i].className.replace(\" w3-white\", \"\");\r\n");
      out.write("  }\r\n");
      out.write("  x[slideIndex-1].style.display = \"block\";  \r\n");
      out.write("  dots[slideIndex-1].className += \" w3-white\";\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
