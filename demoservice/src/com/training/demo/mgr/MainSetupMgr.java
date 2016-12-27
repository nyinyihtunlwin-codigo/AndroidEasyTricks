package com.training.demo.mgr;

import java.sql.Connection;
import java.util.ArrayList;

import com.nirvasoft.rpmini.server.mgr.system.ConnAdmin;
import com.nirvasoft.rpmini.shared.framework.MrBean;
import com.training.demo.dao.MainSetupDAO;
import com.training.demo.dto.Demo;
import com.training.demo.frame.util.ServerUtil;

public class MainSetupMgr {

	public static ArrayList<Demo> getDemoData(MrBean user) {
		// TODO Auto-generated method stub
		ArrayList<Demo> res = new ArrayList<Demo>();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = MainSetupDAO.getDemoData(conn);
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}

	public static String getSaveData(MrBean user, Demo d) {
		// TODO Auto-generated method stub
		String res = null;
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = MainSetupDAO.getSaveData(conn, d);
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}

	public static Demo getData(MrBean user, String name) {
		// TODO Auto-generated method stub
		Demo res = new Demo();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = MainSetupDAO.getData(conn, name);
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}

	public static String getUpdateData(MrBean user, Demo d) {
		String str = null;
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			str = MainSetupDAO.getUpdateData(conn, d);
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return str;
	}

	public static String getDelData(MrBean user, Demo d) {
		// TODO Auto-generated method stub
		String str = null;
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			str = MainSetupDAO.getDelData(conn, d);
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return str;
	}

}
