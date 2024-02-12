package presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import model.Category;
import model.Transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionEditDialogPresenter {

	private Transaction transaction;

	@FXML
	private TextField dateTextField;

	@FXML
	private TextField payeeTextField;

	@FXML
	private TextField categoryTextField;

	@FXML
	private TextField inflowTextField;
	
	private Stage dialogStage;
	
	private boolean approved;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setData(Transaction transaction) {
		this.transaction = transaction;
		updateControls();
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	@FXML
	private void handleOkAction(ActionEvent event) {
		// TODO: implement
		updateModel();
		approved = true;
		dialogStage.close();
	}
	
	@FXML
	private void handleCancelAction(ActionEvent event) {
		// TODO: implement
		dialogStage.close();
	}
	
	private void updateModel() {
		// TODO: implement
		transaction.setDate(convertStringToDate(dateTextField.getText()));
		transaction.setPayee(payeeTextField.getText());
		transaction.setCategory(new Category(categoryTextField.getText()));
		transaction.setInflow(convertStringtoBigDecimal(inflowTextField.getText()));
	}
	
	private void updateControls() {
		// TODO: implement
		dateTextField.textProperty().set(convertDateToString(transaction.getDate()));
		payeeTextField.textProperty().set(transaction.getPayee());
		categoryTextField.textProperty().set(transaction.getCategory().getName());
		inflowTextField.textProperty().set(transaction.getInflow().toString());
	}

	private String convertDateToString(LocalDate date) {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateStringConverter converter = new LocalDateStringConverter(formatter, formatter);
		return converter.toString(date);
	}

	private LocalDate convertStringToDate(String date) {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateStringConverter converter = new LocalDateStringConverter(formatter, formatter);
		return converter.fromString(date);
	}

	private BigDecimal convertStringtoBigDecimal(String number) {
		DecimalFormat decimalFormatter = new DecimalFormat();
		decimalFormatter.setParseBigDecimal(true);
		try {
			return (BigDecimal) decimalFormatter.parse(number);
		} catch (ParseException e) {
			e.printStackTrace();
			return BigDecimal.ZERO;
		}
	}
}
