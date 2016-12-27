package com.training.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.nirvasoft.rpmini.server.singleton.ServerSession;
import com.nirvasoft.rpmini.shared.framework.MrBean;
import com.training.demo.dto.Demo;
import com.training.demo.mgr.MainSetupMgr;

@Path("/demo")
public class MainController {
	@Context
	HttpServletRequest request;

	private MrBean getUser() {
		ServerSession.serverPath = request.getServletContext().getRealPath("/") + "/";
		MrBean user = new MrBean();
		user.getUser().setOrganizationID("001");
		user.getUser().setUserID("admin");
		user.getUser().setUserName("Administrator");
		return user;
	}

	@POST
	@Path("/data/{name}/{nrc}/{email}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Demo getTesting(@PathParam("name") String sname, @PathParam("nrc") String snrc,
			@PathParam("email") String semail) {
		Demo data = new Demo();
		return data;
	}

	@GET
	@Path("/upload")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Demo> getObject() {
		ArrayList<Demo> data = new ArrayList<Demo>();
		data = MainSetupMgr.getDemoData(getUser());
		return data;
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delObject(Demo d) {
		return MainSetupMgr.getDelData(getUser(), d);
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String saveObject(Demo d) {
		return MainSetupMgr.getSaveData(getUser(), d);
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateObject(Demo d) {
		return MainSetupMgr.getUpdateData(getUser(), d);
	}

	@GET
	@Path("/search/{name}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Demo getData(@PathParam("name") String name) {
		return MainSetupMgr.getData(getUser(), name);
	}

	@GET
	@Path("/bobo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getStringData() {
		String data = "ABCD";
		return data;
	}

	@POST
	@Path("/android")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Demo getDBData(Demo d) {
		Demo data = new Demo();
		data.setName(d.getName());
		data.setEmail(d.getEmail());
		data.setNrc(d.getNrc());
		data.setPhno(d.getPhno());
		return data;
	}
}
