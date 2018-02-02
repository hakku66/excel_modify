package front_end;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import back_end.SimpleExcelReaderExample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;

public class Main extends Application {

    private Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(Stage primaryStage) throws Exception {

        final FileChooser fileChooser = new FileChooser();
        SimpleExcelReaderExample excelObj= new SimpleExcelReaderExample();
        Label label_inputfilePath =new Label();
        Label label_resultfilePath =new Label();
        Label label_resultfilename =new Label("Report File name ");
        Button button_selectFile = new Button("Select report to be converted");
        Button button_targetFile = new Button("Select path for the result report");

        Button button_process = new Button("Convert");


        button_selectFile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {

                    List<File> files = Arrays.asList(file);
                    label_inputfilePath.setText(files.get(0).toString());
                }


            }
        });
        button_targetFile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory =
                        directoryChooser.showDialog(primaryStage);

                if(selectedDirectory == null){
                    label_resultfilePath.setText("No Directory selected");
                }else{
                    label_resultfilePath.setText(selectedDirectory.getAbsolutePath());
                }
                TextInputDialog dialog = new TextInputDialog("Report");
                dialog.setTitle("Please enter the file name");
                dialog.setHeaderText("Report File name ");
                dialog.setContentText("Please enter report file name:");


                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    //System.out.println("Your name: " + result.get());
                    label_resultfilename.setText(result.get());
                }

            }
        });

        button_process.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    String arguments = label_inputfilePath.getText();
                    System.out.println(arguments);
                 //   arguments.replace("\","\\");
                    excelObj.readfromExcel(arguments.trim());
                    if(excelObj.writetoExcel(label_resultfilePath.getText()+"\\"+label_resultfilename.getText()+".xlsx")){
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(" Report generated Successfully ");
                        alert.setContentText("What you want to do now" );
                        //+label_resultfilePath.getText()+"! \n Do you want to open the file ???");
                        ButtonType buttonTypeFileOpen = new ButtonType("Open File");
                        ButtonType buttonTypeDirOpen = new ButtonType("Open File Location");
                        ButtonType buttonTypeTermiate = new ButtonType("Exit ");

                        alert.getButtonTypes().setAll(buttonTypeFileOpen, buttonTypeDirOpen, buttonTypeTermiate);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == buttonTypeFileOpen){
                            // ... user chose "One"
                            try {
                                Desktop.getDesktop().open(new File(label_resultfilePath.getText()+"\\"+label_resultfilename.getText()+".xlsx"));
                            } catch (IOException e) {e.printStackTrace();}
                        } else if (result.get() == buttonTypeDirOpen) {
                            // ... user chose "Two"
                            try {
                                Desktop.getDesktop().open(new File(label_resultfilePath.getText()));
                            } catch (IOException e) {e.printStackTrace();}
                        } else if (result.get() == buttonTypeTermiate) {
                            // ... user chose "Three"
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });




        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(5);

        root.getChildren().addAll(label_inputfilePath, button_selectFile,label_resultfilePath,label_resultfilename,button_targetFile,button_process);

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Manjeri HardWare");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void printLog(TextArea textArea, List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(file.getAbsolutePath() + "\n");
        }
    }

    private void openFile(File file) {
        try {
            this.desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
