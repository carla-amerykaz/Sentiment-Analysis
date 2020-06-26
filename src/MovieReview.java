
import java.io.Serializable;



public class MovieReview implements Serializable {
    

    
		private static final long serialVersionUID = 5194530872840629404L;

		/**
         * The id of the review (e.g. 2087).
         */
        private final int id;

        /**
         *  The text of the review.
         */
        private final String text;

        /**
         * The predicted polarity of the review (0 = negative, 1 = positive).
         */
        private int predictedPolarity;

        /**
         * The ground truth polarity of the review (0 = negative, 1 = positive, 2 = unknown).
         */
        private final int realPolarity;

        /**
         * Constructor.
         * @param id
         * @param text
         * @param realPolarity
         */
        public MovieReview(int id, String text, int realPolarity)
        {
        this.id = id;
        this.text = text;
        this.realPolarity = realPolarity;
        this.predictedPolarity = 0; // Set a default value. To be changed later.
        }


        /**
         *
         * @return review id field
         */
        public int getId()
    {
        return id;
    }


        /**
         *
         * @return review text field
         */
        public String getText() {
        return text;
    }

        /**
         *
         * @return predictedPolarity field
         */
        public int getPredictedPolarity() {
        return predictedPolarity;
    }

        /**
         *
         * @param predictedPolarity
         */
        public void setPredictedPolarity(int predictedPolarity) {
        this.predictedPolarity = predictedPolarity;
    }

        /**
         *
         * @return realPolarity
         */
        public int getRealPolarity() {
        return realPolarity;
    }

        public String toString()
        {
        	return "ID: "+id+"\n"+
        			"TEXT: "+text+"\n"+
        			"PREDICTED POLARITY: "+predictedPolarity+"\n"+
        			"REAL POLARITY: "+realPolarity+"\ns";
        }


    }

