package com.esprit.ss.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.ss.domain.User;
import com.esprit.ss.service.UserService;
import com.liferay.portal.service.UserLocalServiceUtil;


@ManagedBean
@SessionScoped
public class MainCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User currentUser;
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public MainCtrl() {
		super();
		com.liferay.portal.model.User portalUser = getCurrentPortalUser();
		if ( portalUser != null) {
			try {
				UserService userDAO = (UserService) new InitialContext().lookup("java:global/StaffingServicesWeb-portlet/UserService!com.esprit.ss.service.UserService");
				if (userDAO.mailExists(portalUser.getEmailAddress())) {
					currentUser = userDAO.getByMail(getCurrentPortalUser().getEmailAddress());
				}
			} catch (NamingException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	// Get the user signed in liferay
		public static com.liferay.portal.model.User getCurrentPortalUser() {
			com.liferay.portal.model.User u = null;
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			if (externalContext.getUserPrincipal() != null) {
				Long id = Long.parseLong(externalContext.getUserPrincipal().getName());
				try {
					u = UserLocalServiceUtil.getUserById(id);
				} catch (Exception e) {
					System.out.println("Could not get currently logged in user");
				}
			}
			return u;
		}

		// populate the database if it contains no user
		public void populate() {
			/*UserService userDAO = null;
			com.esprit.ss.service.EmployeeService employeeDAO = null;
			com.esprit.ss.service.ManagerService managerDAO = null;
			com.esprit.ss.service.AdminService adminDAO = null;
			com.esprit.ss.service.MeetingService meetingDAO = null;
			com.esprit.ss.service.ProjectService projectDAO = null;
			com.esprit.ss.service.TeamService teamDAO = null;
			try {
				userDAO = (com.esprit.ss.service.UserService) new InitialContext().lookup("java:global/StaffingServicesWeb-portlet/UserService!com.esprit.ss.service.UserService");
				employeeDAO = (com.esprit.ss.service.EmployeeService) new InitialContext().lookup("java:global/StaffingServicesWeb-portlet/EmployeeService!com.esprit.ss.service.EmployeeService");
				managerDAO = (com.esprit.ss.service.ManagerService) new InitialContext().lookup("java:global/StaffingServicesWeb-portlet/ManagerService!com.esprit.ss.service.ManagerService");
				adminDAO = (com.esprit.ss.service.AdminService) new InitialContext().lookup("java:global/StaffingServicesWeb-portlet/AdminService!com.esprit.ss.service.AdminService");
				meetingDAO = (com.esprit.ss.service.MeetingService) new InitialContext().lookup("java:global/StaffingServicesWeb-portlet/MeetingService!com.esprit.ss.service.MeetingService");
				projectDAO = (com.esprit.ss.service.ProjectService) new InitialContext().lookup("java:global/StaffingServicesWeb-portlet/ProjectService!com.esprit.ss.service.ProjectService");
				teamDAO = (com.esprit.ss.service.TeamService) new InitialContext().lookup("java:global/StaffingServicesWeb-portlet/TeamService!com.esprit.ss.service.TeamService");
			} catch (NamingException e1) {
				e1.printStackTrace();
			}		
			int count = userDAO.getCount();
			if (count == 0) {
				System.out.println("Populating database...");
				com.esprit.ss.domain.Team t = null;
				for(int i=1;i<3;i++) {
					User u = new User();
					u.setMail("admin"+i+"@example.com");
					u.setLogin("admin"+i);
					u.setTelephoneNumber("("+i+i+") "+i+i+i+"-"+i+i+i);
					//u.setPassword(Encrypt.sha("admin"+i));
					u.setPassword("admin"+i);
					u.setJoinDate(new Date());
					com.esprit.ss.domain.Administrator a = new com.esprit.ss.domain.Administrator(u);
					adminDAO.addAdmin(a);
				}
				for(int i=1;i<5;i++) {
					User u = new User();
					u.setMail("company"+i+"@example.com");
					u.setLogin("company"+i);
					u.setTelephoneNumber("("+i+i+") "+i+i+i+"-"+i+i+i);
					//u.setPassword(Encrypt.sha("company"+i));
					u.setPassword("company"+i);
					u.setJoinDate(new Date());
					com.esprit.ss.domain.Company c = new com.esprit.ss.domain.Company(u);
					managerDAO.addManager(m);
				}
				for(int i=1;i<10;i++) {
					User u = new User();
					u.setMail("jobseeker"+i+"@example.com");
					u.setLogin("jobseeker"+i);
					u.setTelephoneNumber("("+i+i+") "+i+i+i+"-"+i+i+i);
					//u.setPassword(Encrypt.sha("employee"+i));
					u.setPassword("jobseeker"+i);
					u.setJoinDate(new Date());
					com.esprit.ss.domain.JobSeeker e = new com.esprit.ss.domain.JobSeeker(u);
					//e.setSpeciality(com.esprit.ss.domain.Speciality.values()[i % 4]);
					if (i % 3 == 1) {
						t = new com.esprit.ss.domain.Team();
						t.setName("Team"+(i/3+1));
						t.setMotivation("Motivation"+(i/3+1));
						t.setAbout("About"+(i/3+1));
						t.setManager(managerDAO.getById(i/3+1));
						teamDAO.addTeam(t);
					}
					e.setTeam(t);
					employeeDAO.addEmployee(e);
					ldap.addUser(e);
				}
				int k = 6;
				for(int i=1;i<3;i++) {
					Meeting m = new Meeting();
					m.setDate(new Date());
					m.setObjectifs("Objectives"+i);
					m.setPlace("Place1"+i);
					m.setTheme("Theme"+i);
					m.setManager(managerDAO.getById(i));
					List<Employee> employees = new ArrayList<Employee>();
					for (int j=0;j<3;j++) {
						k++;
						employees.add(employeeDAO.getById(k));
					}
					m.setEmployees(employees);
					meetingDAO.addMeeting(m);
				}
				for(int i=1;i<4;i++) {
					Project p = new Project();
					p.setDescription("Description"+i);
					p.setStart(new Date());
					p.setEnd(new Date());
					p.setTechnology("Technology"+i);
					p.setTitle("Title"+i);
					p.setManager(managerDAO.getById(i));
					List<Team> teams = new ArrayList<Team>();
					teams.add(teamDAO.getById(i));
					teams.add(teamDAO.getById(i%3+1));
					p.setTeams(teams);
					projectDAO.addProject(p);
					for ( Team tp : p.getTeams()) {
						tp.getProjects().add(p);
						teamDAO.editTeam(tp);
					}
				}
				System.out.println("Database populated");
			}*/
		}
		
}
