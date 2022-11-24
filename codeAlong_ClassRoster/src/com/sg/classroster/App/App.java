package com.sg.classroster.App;

import com.sg.classroster.Controller.ClassRosterController;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFIleImpl;
import com.sg.classroster.ui.ClassRosterView;
import com.sg.classroster.ui.UserIO;
import com.sg.classroster.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFIleImpl();
        ClassRosterController controller = new ClassRosterController(myDao, myView);
        controller.run();
    }
}
