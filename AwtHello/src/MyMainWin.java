
import java.awt.*;


public class MyMainWin extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int buttonCounterPressed = 0;
	public MyMainWin()
	{
		super();
		this.setSize(300, 200);
		Label label = new Label();
		label.setText("Hello from AWT");
		this.add(label, BorderLayout.NORTH);
		Button button = new Button();
		button.setLabel("Press me");
		button.addActionListener( e->{
			buttonCounterPressed++;
			label.setText(String.valueOf(buttonCounterPressed));
		});
		
		this.add(button, BorderLayout.SOUTH);
		
		
	}
	
	@Override
	public boolean handleEvent(Event evt) {
		if (evt.id == Event.WINDOW_DESTROY)
		{
			setVisible(false);
			System.exit(0);
			return true;
		}
		else
			return super.handleEvent(evt);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			new MyMainWin().setVisible(true);
		});
		

	}

}
