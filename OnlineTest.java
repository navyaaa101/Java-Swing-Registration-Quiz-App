import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class OnlineTest extends JFrame implements ActionListener {
    JLabel questionLabel;
    JRadioButton options[] = new JRadioButton[4];
    JButton nextButton, bookmarkButton, prevButton, resultButton;
    ButtonGroup buttonGroup;
    int count = 0, current = 0, bookmarkCount = 1;
    int bookmarks[] = new int[10];
    String username;

    public OnlineTest(String username) {
        this.username = username;

        questionLabel = new JLabel();
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            buttonGroup.add(options[i]);
        }
        nextButton = new JButton("Next");
        bookmarkButton = new JButton("Bookmark");
        prevButton = new JButton("Previous");
        resultButton = new JButton("Result");

        nextButton.addActionListener(this);
        bookmarkButton.addActionListener(this);
        prevButton.addActionListener(this);
        resultButton.addActionListener(this);

        add(questionLabel);
        for (int i = 0; i < 4; i++) {
            add(options[i]);
        }
        add(nextButton);
        add(prevButton);
        add(bookmarkButton);
        add(resultButton);

        setLayout(null);

        questionLabel.setBounds(30, 40, 450, 20);
        for (int i = 0; i < 4; i++) {
            options[i].setBounds(50, 100 + i * 30, 200, 20);
        }
        nextButton.setBounds(100, 280, 100, 30);
        prevButton.setBounds(210, 280, 100, 30);
        bookmarkButton.setBounds(320, 280, 100, 30);
        resultButton.setBounds(480, 20, 100, 30);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setQuestions();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (checkAnswer()) {
                count++;
            }
            current++;
            setQuestions();
            if (current == 9) {
                nextButton.setEnabled(false);
                bookmarkButton.setEnabled(false);
                resultButton.setVisible(true);
            }
        } else if (e.getSource() == bookmarkButton) {
            JButton bookmarkBtn = new JButton("Bookmark " + bookmarkCount);
            bookmarkBtn.setBounds(480, 20 + 30 * bookmarkCount, 100, 30);
            add(bookmarkBtn);
            bookmarkBtn.addActionListener(this);
            bookmarks[bookmarkCount] = current;
            bookmarkCount++;
            current++;
            setQuestions();
            if (current == 9) {
                bookmarkButton.setEnabled(false);
                resultButton.setVisible(true);
            }
            setVisible(false);
            setVisible(true);
        } else if (e.getSource() == prevButton) {
            if (current > 0) {
                current--;
                setQuestions();
            }
        } else {
            for (int i = 0, y = 1; i < bookmarkCount; i++, y++) {
                if (e.getActionCommand().equals("Bookmark " + y)) {
                    if (checkAnswer())
                        count++;
                    int temp = current;
                    current = bookmarks[y];
                    setQuestions();
                    ((JButton) e.getSource()).setEnabled(false);
                    current = temp;
                }
            }

            if (e.getSource() == resultButton) {
                if (checkAnswer())
                    count++;

                saveResultToCSV(username, count); 
                
                JOptionPane.showMessageDialog(this, "Correct answers: " + count + "\nThank you for taking the quiz!");
                System.exit(0);
            }
        }
    }

    void setQuestions() {
        options[0].setSelected(true);
        
        switch (current) {
            case 0:
                questionLabel.setText("Que1: Which one among these is not a datatype");
                options[0].setText("int");
                options[1].setText("Float");
                options[2].setText("boolean");
                options[3].setText("char");
                break;
            case 1:
                questionLabel.setText("Que2: Which class is available to all classes automatically");
                options[0].setText("Swing");
                options[1].setText("Applet");
                options[2].setText("Object");
                options[3].setText("ActionEvent");
                break;
            case 2:
                questionLabel.setText("Que3: Which package is directly available to our class without importing it");
                options[0].setText("swing");
                options[1].setText("applet");
                options[2].setText("net");
                options[3].setText("lang");
                break;
            case 3:
                questionLabel.setText("Que4: String class is defined in which package");
                options[0].setText("lang");
                options[1].setText("Swing");
                options[2].setText("Applet");
                options[3].setText("awt");
                break;
            case 4:
                questionLabel.setText("Que5: Which institute is best for Java coaching");
                options[0].setText("Utek");
                options[1].setText("Aptech");
                options[2].setText("SSS IT");
                options[3].setText("jtek");
                break;
            case 5:
                questionLabel.setText("Que6: Which one among these is not a keyword");
                options[0].setText("class");
                options[1].setText("int");
                options[2].setText("get");
                options[3].setText("if");
                break;
            case 6:
                questionLabel.setText("Que7: Which one among these is not a class");
                options[0].setText("Swing");
                options[1].setText("Actionperformed");
                options[2].setText("ActionEvent");
                options[3].setText("Button");
                break;
            case 7:
                questionLabel.setText("Que8: Which one among these is not a function of the Object class");
                options[0].setText("toString");
                options[1].setText("finalize");
                options[2].setText("equals");
                options[3].setText("getDocumentBase");
                break;
            case 8:
                questionLabel.setText("Que9: Which function is not present in the Applet class");
                options[0].setText("init");
                options[1].setText("main");
                options[2].setText("start");
                options[3].setText("destroy");
                break;
            case 9:
                questionLabel.setText("Que10: Which one among these is not a valid component");
                options[0].setText("JButton");
                options[1].setText("JList");
                options[2].setText("JButtonGroup");
                options[3].setText("JTextArea");
                break;
        }

        questionLabel.setBounds(30, 40, 450, 20);
    }

    boolean checkAnswer() {
        switch (current) {
            case 0:
                return options[1].isSelected();
            case 1:
                return options[2].isSelected();
            case 2:
                return options[3].isSelected();
            case 3:
                return options[0].isSelected();
            case 4:
                return options[2].isSelected();
            case 5:
                return options[2].isSelected();
            case 6:
                return options[1].isSelected();
            case 7:
                return options[3].isSelected();
            case 8:
                return options[1].isSelected();
            case 9:
                return options[2].isSelected();
            default:
                return false;
        }
    }

    void saveResultToCSV(String name, int score) {
        String csvFile = "result.csv";
        File file = new File(csvFile);

        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("Name: " + name + " | Quiz Score: " + score);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
