<%@page import="common.*"%>
<%@page import="data.*"%>
<%@page import="backingbean.*"%>
<%  
					String username = Constants.EMPTY_STRING;
					loginBackingBean loginBackingBean = (loginBackingBean) Util.getBean("loginBackingBean"); 
					if(loginBackingBean != null)
					{
						LoginData loginData = loginBackingBean.getStateData();
						if(loginData !=null)
						{
							String name = "";
							if(loginData.getFirstName() != null && !"null".equalsIgnoreCase(loginData.getFirstName()))
								name = loginData.getFirstName() ;
							if(loginData.getLastName() != null && !"null".equalsIgnoreCase(loginData.getLastName()))
								name += loginData.getLastName();
							if(!Util.isNullTrimmedString(name))
								username = loginData.getFirstName() + "    " + loginData.getLastName();
						}
					}
				
				%>
</div>
      <div class="sidebar">
        <div class="gadget">
          <div class="search">
            <form method="get" id="search" action="itemList.jsp">
              <span>
              <input type="text" value="Search..." name="searchText" id="searchText" size="38" onfocus="clearSearch()"/>
              <input name="searchsubmit" type="image" src="images/search.gif" value="Go" id="searchsubmit" class="btn"  />
              </span>
            </form>
            <!--/searchform -->
            <div class="clr"></div>
          </div>
          <div class="clr"></div>
        </div>
        <div class="gadget">
        	<div class="marquee">
         		<h2>Hi <%= username %></h2>
         	</div>
          <div class="clr"></div>
          <ul class="sb_menu">
            <li class="active"><a href="index.jsp">Categories</a></li>
			<li><a href="orders.jsp">Orders</a></li>
			<li><a href="myAccount.jsp">My Account</a></li>
            <li><a href="contact.jsp">Contact us</a></li>
            <li><a href="about.jsp">About us</a></li>
          </ul>
        </div>
        <div class="gadget">
          <h2><span></span></h2>
          <div class="clr"></div>
        
        </div>
        <div class="gadget">
          <h2 class="grey"><span>Wise Words</span></h2>
          <div class="clr"></div>
          <div class="testi">
            <p><span class="q"><img src="images/quote_1.gif" width="16" height="14" alt="quote" /></span> Best shopping you can ever experience. <span class="q"><img src="images/quote_2.gif" width="16" height="14" alt="quote" /></span></p>
            <p class="title"><strong>OSM</strong></p>
          </div>
        </div>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="fbg">
    <div class="fbg_resize">
      <div class="col c1">
        <h2><span>Image Gallery</span></h2>
        <a href="#"><img src="images/pic_1.jpg" width="58" height="58" alt="pix" /></a> <a href="#"><img src="images/pic_2.jpg" width="58" height="58" alt="pix" /></a> <a href="#"><img src="images/pic_3.jpg" width="58" height="58" alt="pix" /></a> <a href="#"><img src="images/pic_4.jpg" width="58" height="58" alt="pix" /></a> <a href="#"><img src="images/pic_5.jpg" width="58" height="58" alt="pix" /></a> <a href="#"><img src="images/pic_6.jpg" width="58" height="58" alt="pix" /></a> </div>
      <div class="col c2">
         </div>
      <div class="col c3">
        </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="footer">
    <div class="footer_resize">
       <p class="lf">© Copyright OSM</p>
      <div class="clr"></div>
    </div>
  </div>
</div>
</body>
</html>