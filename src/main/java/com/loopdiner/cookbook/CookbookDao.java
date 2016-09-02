package com.loopdiner.cookbook;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.loopdiner.comman.LdUtils;
import com.loopdiner.comman.QueryCond;
import com.project.dao.impl.BaseDaoImpl;

@Repository(value="cookbookDao")
@Transactional
public class CookbookDao extends BaseDaoImpl<Cookbook>{

	public CookbookDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Cookbook saveCookbook(Cookbook cookbook){
		cookbook.setImg(LdUtils.getPicUrl(cookbook.getImg()));
		cookbook.setDate(LdUtils.getDateStr());
		Cookbook addedCookbook = this.add(cookbook);
		cookbook.setId(addedCookbook.getId());
		
		if(cookbook.getMaterials() != null){
			for(CookbookMaterial cookbookMaterial:cookbook.getMaterials()){ 
				cookbookMaterial.setCookbookid(cookbook.getId());
				CookbookMaterial addedCookbookMaterial = (CookbookMaterial) this.sessionFactory
						.getCurrentSession().merge(cookbookMaterial);
				cookbookMaterial.setId(addedCookbookMaterial.getId());
			}
		}
		
		if(cookbook.getSteps()!=null){
			for(CookbookSteps cookbookSteps:cookbook.getSteps()){ 
				cookbookSteps.setImg(LdUtils.getPicUrl(cookbookSteps.getImg()));
				cookbookSteps.setCookbookid(cookbook.getId());
				CookbookSteps addedCookbookSteps = (CookbookSteps) this.sessionFactory
						.getCurrentSession().merge(cookbookSteps);
				cookbookSteps.setId(addedCookbookSteps.getId());
			}
		}	
		
		return cookbook;
	}
	
	public Cookbook updateCookbook(Cookbook cookbook){
		cookbook.setImg(LdUtils.getPicUrl(cookbook.getImg()));
		cookbook.setDate(LdUtils.getDateStr());
		Session session = sessionFactory.getCurrentSession();
		StringBuffer ql = new StringBuffer();
		ql.append("update Cookbook t set t.name =:name,")
			.append("t.img=:img,")
			.append("t.intro=:intro, ")
			.append("t.date=:date ")
			.append("  where t.id =:id");
		Query query = session.createQuery(ql.toString());
		query.setString("name", cookbook.getName())
		.setString("img", cookbook.getImg())
		.setString("intro", cookbook.getIntro())
		.setInteger("id", cookbook.getId())
		.setString("date", cookbook.getDate());
		query.executeUpdate();
		
		session.createSQLQuery("delete from cookbook_material where cookbookid=:cookbookid")
		.setInteger("cookbookid", cookbook.getId()).executeUpdate();
		if(cookbook.getMaterials() != null){
			for(CookbookMaterial  cookbookMaterial:cookbook.getMaterials()){
				cookbookMaterial.setCookbookid(cookbook.getId());
				this.sessionFactory.getCurrentSession().merge(cookbookMaterial);
			}
		}		
		
		session.createSQLQuery("delete from cookbook_steps where cookbookid=:cookbookid")
		.setInteger("cookbookid", cookbook.getId()).executeUpdate();
		if(cookbook.getSteps()!=null){
			for(CookbookSteps cookbookSteps:cookbook.getSteps()){
				cookbookSteps.setImg(LdUtils.getPicUrl(cookbookSteps.getImg()));
				cookbookSteps.setCookbookid(cookbook.getId());
				this.sessionFactory.getCurrentSession().merge(cookbookSteps);
			}
		}
		return cookbook;
	}
	
	
	public List<CookbookResult> getCookbookMsg(QueryCond queryCond){
		Integer page = 0;
		if(queryCond.getPageindex()>0){
			page = queryCond.getPageindex() - 1;
		}
		int startIndex = page*queryCond.getPageCount();
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select u.name as name,u.img as img,b.img as userimg,u.userid as userid ")
					.append("from cookbook u,user b ")
					.append("where u.userid=b.id ")
					.append("order by u.date desc ");
		Query query = session.createSQLQuery(sql.toString())
				.addScalar("name")
				.addScalar("img")
				.addScalar("userimg")
				.addScalar("userid")
				.setFirstResult(startIndex).setMaxResults(queryCond.getPageCount());
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		List<CookbookResult> results = new ArrayList<CookbookResult>();
		for(Object[] obj : result) {
			CookbookResult rr = new CookbookResult();
			rr.setName((String)obj[0]);
			rr.setImg((String)obj[1]);
			rr.setUserimg((String)obj[2]);
			rr.setUserid((Integer)obj[3]);
			results.add(rr);
		}
		return results;
	}
	
	public Cookbook getCookbook(Integer cookbookId) {
		Cookbook cookbook = this.queryOne("Cookbook", cookbookId);
		
		Session session  = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from CookbookMaterial m where m.cookbookid=:cookbookid order by queueId ")
							.setInteger("cookbookid", cookbookId);
		@SuppressWarnings("unchecked")
		List<CookbookMaterial> materials = query.list();
		cookbook.setMaterials(materials);
		
		Query query1 = session.createQuery("from CookbookSteps m where m.cookbookid=:cookbookid order by queueId ")
				.setInteger("cookbookid", cookbookId);

		@SuppressWarnings("unchecked")
		List<CookbookSteps> steps = query1.list();
		cookbook.setSteps(steps);
		
		return cookbook;
	}
}
