package com.training.demo.appswitch;

import java.util.ArrayList;

import com.nirvasoft.rpmini.shared.framework.MrBean;
import com.training.demo.dto.Demo;
import com.training.demo.mgr.MainSetupMgr;

public class MainAppSwitch {

	public static ArrayList<Demo> getDemoData(MrBean user) {
		// TODO Auto-generated method stub
		return MainSetupMgr.getDemoData(user);
	}

}
