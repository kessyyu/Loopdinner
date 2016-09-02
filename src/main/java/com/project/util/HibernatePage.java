package com.project.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class HibernatePage {
	public static List findOnePage(Session session, String strHQL, int offset, int pagesize) {
		List lst = null;
		try {
			Query query = session.createQuery(strHQL);
			if (offset != 0 && pagesize != 0) {
				query.setFirstResult((offset - 1) * pagesize);
				query.setMaxResults(pagesize);
			}
			lst = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lst;
	}

	public int totalRecord(Session session, String strHQL, int pagesize) {
		Query query = session.createQuery(strHQL);
		String count = (query.iterate().next()).toString();
		System.out.println("COUNT" + count);
		int n = Integer.parseInt(count);
		int total = (n + pagesize - 1) / pagesize;

		return total;
	}

	public PagesDomain turnPage(int cpage, int pagesize, int count) {
		int form_page;
		int wholepage;
		PagesDomain pd = new PagesDomain();
		int pages = count / pagesize;
		int extra_page = count % pagesize;
		if(extra_page > 0){
			wholepage = pages +1;
		}else{
			wholepage = pages;
		}
		System.out.println("Wholepage:"+wholepage);
		if(cpage <= 0){
			cpage = 1;
			form_page = 0;
		}
			
		else if(cpage < wholepage && cpage > 0){
			form_page = pagesize*(cpage-1);
		}
		else{
			form_page = pagesize*(wholepage-1);
		}
		if(cpage >= wholepage){
			cpage = wholepage;
		}

		pd.setCpage(cpage);
		pd.setPagesize(pagesize);
		pd.setFrom_page(form_page);
		pd.setTotal(count);
		return pd;
	}
}
