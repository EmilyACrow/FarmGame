package gameLogic;

import gameScreens.WelcomeScreen;

public class Welcome {
	WelcomeScreen m_screen;
	public static void main(String args[])
	{
		Welcome welcome = new Welcome();
		welcome.run(welcome);
	}
	
	public void run(Welcome welcome)
	{
		m_screen = new WelcomeScreen(welcome);
		m_screen.setVisible(true);
	}
	
	public void startGame()
	{
		try
		{
			new GameEnvironment(m_screen.getFarmName()
								, m_screen.getFarmType()
								, new Farmer(m_screen.getFarmerName(), m_screen.getFarmerAge())
								, m_screen.getNumDays());
			m_screen.setVisible(false);
		}
		catch(Exception e)
		{
			m_screen.setText(e.getMessage());
		}
	}
}