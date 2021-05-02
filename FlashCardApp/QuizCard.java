
import java.io.*;

public class QuizCard implements Serializable {

        private String question;
        private String answer;

        public QuizCard(String q, String a) {
          question = q;
          answer = a;
        }

        public String getAnswer () {
            return answer;
        }

        public void setAnswer(String a) {
            answer = a;
        }

        public String getQuestion () {
            return question;
        }

        public void setQuestion(String q) {
            question = q;
        } 
}     

