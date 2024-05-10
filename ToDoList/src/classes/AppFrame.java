package classes;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AppFrame extends JFrame{

	private TitleBar title;
	private List list;
	private ButtonPanel btnPanel;
	
	private JButton addTask;
	private JButton clear;
	//Constructor
	AppFrame(){
		this.setSize(400,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		title = new TitleBar();
		list = new List();
		btnPanel = new ButtonPanel();
		this.add(title,BorderLayout.NORTH);
		this.add(btnPanel,BorderLayout.SOUTH);
		
		this.add(list, BorderLayout.CENTER);
		
		addTask = btnPanel.getAddTask();
		clear = btnPanel.getClear();
		addListeners();
	}
	
	public void addListeners() 
	{
		addTask.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				Task task = new Task();
				list.add(task);
				list.updateNumber();
				
				task.getDone().addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e)
					{
						task.changeState();
						revalidate();
					}
				});
				revalidate();
			}
		});
		
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				for (int i = 0; i < list.getComponentCount(); i++) {
		            // Get the component (task) at index i
		            Task task = (Task) list.getComponent(i);
		            // Check if the task is done (checked)
		            if (task.checked) {
		                // Remove the task
		                list.remove(task);
		                i--; // Since we removed a task, adjust the index
		            }
		        }
		        // Update the display
		        list.revalidate();
		        list.repaint(); 
			}
			
		});
	}
}
