import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shahbaaz on 1/21/17.
 */
public class SpeechHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;

    static {
        supportedApplicationIds = new HashSet<String>();
    }

    public SpeechHandler() {
        super(new SessionSpeechlet(), supportedApplicationIds);
    }
}
