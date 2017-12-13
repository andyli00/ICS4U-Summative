package main.managers;

import organization.Task;

import java.util.ArrayList;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import utilities.FxDialog;


/**
 * A class to create and manage project and task objects
 * @author Andy Li
 * @since Dec 04, 2017
 */
public class ProjectManager {
	
	private static ArrayList<Task> projects;
	
	public ProjectManager() {
		run();
	}
	
	private void run() {
		/*Task fruit = new Task("fruit", 2, false);
		fruit.getTaskList().add(new Task("apples"));
		fruit.getTaskList().add(new Task("grapes"));
		
		Task clothes = new Task("clothes", 2, false);
		clothes.getTaskList().add(new Task("jeans"));
		clothes.getTaskList().add(new Task("shirts"));
		
		projects = new ArrayList<>();
		projects.add(new Task("Shopping", 3, true));
		projects.get(0).getTaskList().add(new Task("Groceries", 2, false));
		projects.get(0).getTaskList().get(0).getTaskList().addAll(fruit.getTaskList());
		projects.get(0).getTaskList().add(new Task("get gas"));
		projects.get(0).getTaskList().add(clothes);
		
		saveProjectData();*/
		
		
	}
	
	private void saveProjectData() {
		File projectFolder = new File(System.getProperty("user.home") + "/.common_management/projects");
		
		projectFolder.mkdirs();
		File[] projectFiles = projectFolder.listFiles();
		
		for (File project : projectFiles) {
			if (project.isFile())
				project.delete();
		}
		
		
		for (Task task: projects) {
			try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				
				Document document = documentBuilder.newDocument();
				Element rootElement = document.createElement("project");
				rootElement.setAttribute("completed", Boolean.toString(task.isProject()));
				rootElement.setAttribute("size" , Integer.toString(task.getTaskList().size()));
				rootElement.setAttribute("name", task.getName());
				document.appendChild(rootElement);
				
				ArrayList<Element> children = new ArrayList<>(task.getNumChildren(task));
				for (Element e: children) {
					createAttributes(children, document, task);
				}
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(document);
				
				StreamResult result = new StreamResult(projectFolder.getAbsolutePath() + "/project_" + task.getName() + ".xml");
				
				transformer.transform(source, result);
			} catch (ParserConfigurationException | TransformerException e) {
				FxDialog.showError("Error saving project:", e.getMessage());
			}
		}
	}
	
	private void createAttributes(ArrayList<Element> list, Document document, Task task) {
		for (int i = 0; i < task.getTaskList().size(); i++) {
			//adds the attribute "name"
			list.set(i, document.createElement(task.getTaskList().get(i).getName()));
			
			//adds the attribute "completed" with value "true" or "false"
			list.get(i).setAttribute("completed", Boolean.toString(task.getTaskList().get(i).isCompleted()));
			
			if (task.isList()) { //if this element is a todolist
				if (task.getTaskList().get(i).isList()) {
					list.get(i).setAttribute("size", Integer.toString(task.getTaskList().get(i).getTaskList().size()));
					createAttributes(list, document, task.getTaskList().get(i)); //recursion!
					document.appendChild(list.get(i));
				}
			}
			else { //if this element is just a plain task
				document.appendChild(list.get(i));
			}
		}
	}
	
	private void loadProjectData(Task task) {
		File projectFolder = new File(System.getProperty("user.home") + "/.common_management/projects");
		projectFolder.mkdirs();
		File[] projectFiles = projectFolder.listFiles();
		
		int i = 0;
		for (File project: projectFiles) {
			try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(project);
				
				document.getDocumentElement().normalize();
				
				readAttributes(task, document);
				
				i++;
			}
			catch (Exception e){
				FxDialog.showError("Error", e.getMessage());
			}
		}
	}
	
	private void readAttributes(Task task, Document document) {
		NodeList nodeList = document.getElementsByTagName(task.getName());
	}
	
	public static ArrayList<Task> getProjects() {
		return projects;
	}
}
