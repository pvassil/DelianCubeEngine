package client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class RunQueryHelpController extends AbstractController {
	@FXML
	private Label titleLabel;

	@FXML
	private TextArea shortDescrTextArea;
	@FXML
	private TextArea syntaxTextArea;	
	@FXML
	private TextArea commentsTextArea;

	public void setup() {
		titleLabel.setText("Run Query");
		shortDescrTextArea.setText("This functionality allows the execution of cube queries via the query string authored in the text area of the window");
		syntaxTextArea.appendText("CubeName: <cubename>\n");
		syntaxTextArea.appendText("Name: <queryName>\n");
		syntaxTextArea.appendText("AggrFunc: <aggr.function>\n");
		syntaxTextArea.appendText("Measure: <the measure aggregated>\n");
		syntaxTextArea.appendText("Gamma: <dim1.aggrLevel>,<dim2.aggrLevel>\n");
		syntaxTextArea.appendText("Sigma: comma separared list of atoms: dim.level='value'\n");

		commentsTextArea.appendText("EXAMPLES\n\n");
		commentsTextArea.appendText("CubeName:loan\n");
		commentsTextArea.appendText("Name: LoanQuery12\n");
		commentsTextArea.appendText("AggrFunc:Max\n");
		commentsTextArea.appendText("Measure:amount\n");
		commentsTextArea.appendText("Gamma:account_dim.lvl1, status_dim.lvl1\n");
		commentsTextArea.appendText("Sigma:date_dim.lvl2 = '1998-01'\n");
		commentsTextArea.appendText("\n");
		commentsTextArea.appendText("CubeName:loan\n");
		commentsTextArea.appendText("Name: LoanQuery21\n");
		commentsTextArea.appendText("AggrFunc:Min\n");
		commentsTextArea.appendText("Measure:amount\n");
		commentsTextArea.appendText("Gamma:account_dim.lvl1,date_dim.lvl2\n");
		commentsTextArea.appendText("Sigma:account_dim.lvl2='Prague', date_dim.lvl3 = '1998'\n");		
	}//end setup method
}//end class
