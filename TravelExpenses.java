import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.DecimalFormat;

public class TravelExpenses extends JFrame
{
   private JPanel panelCenter;
   private JPanel panelButton;
 	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
   private JLabel label10; 	
	
	private JTextField days;
	private JTextField air;
	private JTextField carRentalActual;
	private JTextField miles;
   private JTextField gas; 
	private JTextField parkingActual;
	private JTextField taxiActual;
	private JTextField reg;
	private JTextField lodgingPerNightActual;
	private JTextField mealPerDay;
	
	private final double DAILY_MEAL_RATE = 37.00;
	private final double MAX_PARKING_FEE = 10.00;
	private final double MAX_TAXI_FEE = 20.00;
	private final double MAX_LODGING_PER_NIGHT = 95.00;
	private final double MILES_RATE = 0.27;
	
	private JButton resetButton;
	private JButton calcButton;
	
	private final int WINDOW_WIDTH = 350;
	private final int WINDOW_HEIGHT = 300;
	

	public TravelExpenses()
	{
		JOptionPane.showMessageDialog(null, "Your company's policy on the reimbursement for your business trip:"
			+ "\nFor meals: $37.00 per day\nFor parking(when a private vehicle was used): $20.00 per day"
				+ "\nFor taxi fee: $20.00 per day \nFor lodging fee: $95.00 per day \nFor gas(when a private" 
					+ " vehicle was used): $0.27 per mile driven \n\nCAUTION: You will not be reimbursed for taxi fee" 
						+ " if a private vehicle was used or a car was rented." + "\n\nAdditionally, your company will reimburse the following:" 
							+ "\n-airfare\n-car rental fees\n-conference or seminar resistration fees");	
	
		setTitle("Travel Expenses Calculator");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      buildCenterPanel();
      buildSouthPanel();      
        	        
              
	  add(panelCenter, BorderLayout.CENTER);
     add(panelButton, BorderLayout.SOUTH);
     
                                
	  setVisible(true);						
	}
   
   public void buildCenterPanel()
   {
      
      
		label1 = new JLabel("Number of days on the trip: ");
		days = new JTextField(10);
		
		label2 = new JLabel("Amount of airfare: ");
		air = new JTextField(10);
		
		label3 = new JLabel("Amount of car rental: ");
		carRentalActual = new JTextField(10);
		
		label4 = new JLabel("Miles driven: ");
		miles = new JTextField(10);
      
      label10 = new JLabel("Gas cost per mile: ");
		gas = new JTextField(10);
		
		label5 = new JLabel("Parking fees: ");
		parkingActual = new JTextField(10);
		
		label6 = new JLabel("Taxi fees: ");
		taxiActual = new JTextField(10);
		
		label7 = new JLabel("Conference registration : ");
		reg = new JTextField(10);
		
		label8 = new JLabel("Lodging charges per night: ");
		lodgingPerNightActual = new JTextField(10);
		
		label9 = new JLabel("Meal fee per day: ");
		mealPerDay = new JTextField(10);
		
		   panelCenter = new JPanel();
   		   		
         panelCenter.setLayout(new GridLayout(11,2)); 
              
         panelCenter.add(label1);
         panelCenter.add(days);
         panelCenter.add(label2);
         panelCenter.add(air);
         panelCenter.add(label3);
         panelCenter.add(carRentalActual);
         panelCenter.add(label4);
         panelCenter.add(miles);
         panelCenter.add(label10);
         panelCenter.add(gas);
         panelCenter.add(label5);
         panelCenter.add(parkingActual);
         panelCenter.add(label6);
         panelCenter.add(taxiActual);
         panelCenter.add(label7);
         panelCenter.add(reg);
         panelCenter.add(label8);
         panelCenter.add(lodgingPerNightActual);
         panelCenter.add(label9);
         panelCenter.add(mealPerDay);
                 
         panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


   }
   
   public void buildSouthPanel()
   {
         panelButton = new JPanel();

         resetButton = new JButton("Reset");
   		calcButton = new JButton("Calculate Expenses");

       
         panelButton.add(resetButton);
   		panelButton.add(calcButton);
         
         		
   		resetButton.addActionListener(new ResetButtonListener());
   		calcButton.addActionListener((new CalcButtonListener()));

   }
   
	  	
	private class ResetButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			days.setText("0");
			air.setText("0");
			carRentalActual.setText("0");
			miles.setText("0");
            gas.setText("0");
			parkingActual.setText("0");
			taxiActual.setText("0");
			reg.setText("0");
			lodgingPerNightActual.setText("0");
			mealPerDay.setText("0");
		}
	}
	
	private class CalcButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
            boolean taxiEligibility = true;
			int daysEntered = Integer.parseInt(days.getText());;
         
			double airEntered = Double.parseDouble(air.getText());
			double carRentalEntered = Double.parseDouble(carRentalActual.getText());
			double milesEntered = Double.parseDouble(miles.getText());
            double gasEntered = Double.parseDouble(gas.getText());
			double parkingEntered = Double.parseDouble(parkingActual.getText());
			double taxiEntered = Double.parseDouble(taxiActual.getText());
			double regEntered = Double.parseDouble(reg.getText());
			double lodgingEntered = Double.parseDouble(lodgingPerNightActual.getText());
			double mealPerDayEntered = Double.parseDouble(mealPerDay.getText());
			
			double totalExpenses = 0.0;
			double allowable = 0.0;
			double excess = 0.0;
			double saved = 0.0;
            double reimbursable = 0.0;
			
			totalExpenses = airEntered + carRentalEntered + milesEntered*gasEntered + taxiEntered + 
				parkingEntered + regEntered + daysEntered*lodgingEntered + daysEntered*mealPerDayEntered;
            
         allowable = daysEntered * (DAILY_MEAL_RATE + MAX_LODGING_PER_NIGHT);
			
			if(airEntered>0.0)
			allowable+=airEntered;
         
			if(carRentalEntered>0.0)
         {
			   allowable+=carRentalEntered;
            taxiEligibility = false;
         }   
			if(parkingEntered>0.0)
         {
			   allowable+=daysEntered*MAX_PARKING_FEE;    
            taxiEligibility = false;
         }
  			if(milesEntered>0.0)
         {
			   allowable+=milesEntered*MILES_RATE;
            taxiEligibility = false;
         }
			if(taxiEligibility)
			allowable+=daysEntered*MAX_TAXI_FEE;
         
         if(regEntered>0.0)
         allowable+=regEntered;
			
			if(totalExpenses>allowable)
         {
			   excess = totalExpenses - allowable;
            reimbursable = totalExpenses - excess;
         }
			else if(allowable>totalExpenses)
         {
			   saved = allowable - totalExpenses;
            reimbursable = totalExpenses;
         }
         else if(allowable==totalExpenses)
         {
            reimbursable = totalExpenses;
         }
         
         DecimalFormat formatter = new DecimalFormat("0,000.00");
			
			if(excess>0.0)
			JOptionPane.showMessageDialog(null, "Total expenses: $" + formatter.format(totalExpenses)
          + "\nAllowable amount: $" + formatter.format(allowable) + "\nReimbursable smount: $" + formatter.format(reimbursable)
          + "\n\nExcess Amount: $" + formatter.format(excess));
			else if(saved>0.0)
			JOptionPane.showMessageDialog(null, "Total expenses: $" + formatter.format(totalExpenses) 
         + "\nAllowable amount: $" + formatter.format(allowable) + "\nReimbursable smount: $" + formatter.format(reimbursable) +
            "\n\nSaved Amount: $" + formatter.format(saved));
			else 
			JOptionPane.showMessageDialog(null, "Total expenses: $" + formatter.format(totalExpenses)
          + "\nAllowable amount: $" + formatter.format(allowable)
          + "\nReimbursable smount: $" + formatter.format(reimbursable));
			
			
		}
	}
   
	}

