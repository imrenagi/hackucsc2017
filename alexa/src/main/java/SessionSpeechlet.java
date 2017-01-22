import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

/**
 * Created by shahbaaz on 1/21/17.
 */
public class SessionSpeechlet implements Speechlet {

    public static final String WELCOME_MESSAGE = "Hi! I’m Chef, your virtual host today. " +
            "I’d love to help you with anything! You can talk to me anytime by saying - Alexa, ask " +
            "Chef, followed by your question.";

    public static final String OPTIONS_PROMPT = "You can ask me about a dish, order something, or make your payment";

    public static final String OPTIONS = "Here's some things you can say. What do you have in Chinese? " +
            "Tell me today's special. Describe the flat iron steak. I'd like to make my payment.";

    public static SpeechletResponse getWelcomeResponse() {
        String speechText = WELCOME_MESSAGE;
        String repromptText = OPTIONS_PROMPT;

        return getSpeechletResponse(speechText, repromptText, true);
    }

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
        // initialization
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
        return getWelcomeResponse();
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
        // cleanup
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        switch (intentName) {
            case "IWantWaterIntent": return getWater(intent, session);
            case "AskAboutChickenBurgerIntent": return getChickenBurgerDescription(intent, session);
            case "YesPleaseIntent": return getYesPlease(intent, session);
            case "AlsoGetFriesIntent": return getBurgerFriesConfirmedOrder(intent, session);
            case "WhatsGoodIntent": return getWhatsGoodHere(intent, session);
            case "VegetarianFoodOnlyIntent": return getVegetarianSaladReco(intent, session);
            case "TellMeMoreIntent": return tellMeMore(intent, session);
            case "OrderThatIntent": return getOrderThatForMe(intent, session);
            case "WhiteWineIntent": return getSaladWhiteWineConfirmedOrder(intent, session);
            case "HeinekenBottleIntent": return getHeinekenBottle(intent, session);
            case "DessertSpecialIntent": return getDessertSpecial(intent, session);
            case "OrderTiramisuIntent": return orderTiramisu(intent, session);
            case "IWantToPayIntent": return startPayment(intent, session);
            case "TwoPaymentsIntent": return doFirstPayment(intent, session);
            case "PaymentDoneIntent": {
                String payment = (String) session.getAttribute("payment");
                if(payment.equals("done")) {
                    return goodbyeMessage(intent, session);
                }
                return doSecondPayment(intent, session);
            }
            default: return getSpeechletResponse("I couldn't understand that. Please try again.",
                    "I couldn't understand that. Please try again.", true);
        }
    }

    private SpeechletResponse getWater(final Intent intent, final Session session) {
        String speechText = "Sure! One water is on its way to you right now.";
        SpeechletResponse response = getSpeechletResponse(speechText, speechText, false);
        SimpleCard card = new SimpleCard();
        card.setTitle("Table 16: New drink order!");
        card.setContent("One glass of water");
        response.setCard(card);
        return response;
    }

    private SpeechletResponse getChickenBurgerDescription(final Intent intent, final Session session) {
        String speechText = "1/2 lb ground chicken burger, cheddar, tomato, lettuce, pickles, onions, mayo and brown mustard. Shall I order that for you?";
        String repromptText = "Shall I order that for you?";
        return getSpeechletResponse(speechText, speechText, true);
    }

    private SpeechletResponse getYesPlease(final Intent intent, final Session session) {
        String speechText = "Would you like anything to go with that?";
        String repromptText = "Any sides?";
        return getSpeechletResponse(speechText, repromptText, true);
    }

    private SpeechletResponse getBurgerFriesConfirmedOrder(final Intent intent, final Session session) {
        String speechText = "Great! One chicken burger and fries, coming up in about 15 minutes";
        SpeechletResponse response = getSpeechletResponse(speechText, speechText, false);
        SimpleCard card = new SimpleCard();
        card.setTitle("Table 16: New order!");
        card.setContent("One order of chicken burger and fries");
        response.setCard(card);
        return response;
    }

    private SpeechletResponse getWhatsGoodHere(final Intent intent, final Session session) {
        String speechText = "Do you have any preferences?";
        String repromptText = "Anything in particular?";
        return getSpeechletResponse(speechText, repromptText, true);
    }

    private SpeechletResponse getVegetarianSaladReco(final Intent intent, final Session session) {
        String speechText = "Our most popular vegetarian dish is the caesar salad. Would you like that?";
        String repromptText = "Shall I order the caesar salad?";
        return getSpeechletResponse(speechText, repromptText, true);
    }

    private SpeechletResponse tellMeMore(final Intent intent, final Session session) {
        String speechText = "This is a classic caesar salad with shaved manchego cheese, green olives and creamy anchovy-tarragon dressing";
        String repromptText = "Would you like this salad?";
        return getSpeechletResponse(speechText, repromptText, true);
    }

    private SpeechletResponse getOrderThatForMe(final Intent intent, final Session session) {
        String speechText = "Would you like anything to go with it? I would suggest the pina colada.";
        String repromptText = "Anything to go with it?";
        return getSpeechletResponse(speechText, repromptText, true);
    }

    private SpeechletResponse getSaladWhiteWineConfirmedOrder(final Intent intent, final Session session) {
        String speechText = "Great! One caesar salad and pina colada, coming up in about 10 minutes";
        SpeechletResponse response = getSpeechletResponse(speechText, speechText, false);
        SimpleCard card = new SimpleCard();
        card.setTitle("Table 16: New order!");
        card.setContent("One order of caesar salad\nOne pina colada");
        response.setCard(card);
        return response;
    }

    private SpeechletResponse getHeinekenBottle(final Intent intent, final Session session) {
        String speechText = "Absolutely, it’s on its way to you.";
        SpeechletResponse response = getSpeechletResponse(speechText, speechText, false);
        SimpleCard card = new SimpleCard();
        card.setTitle("Table 16: New drink order!");
        card.setContent("One bottle of Heineken beer");
        response.setCard(card);
        return response;
    }

    private SpeechletResponse getDessertSpecial(final Intent intent, final Session session) {
        String speechText = "The special today is the tiramisu.";
        return getSpeechletResponse(speechText, speechText, false);
    }

    private SpeechletResponse orderTiramisu(final Intent intent, final Session session) {
        String speechText = "One tiramisu, coming right up.";
        SpeechletResponse response = getSpeechletResponse(speechText, speechText, false);
        SimpleCard card = new SimpleCard();
        card.setTitle("Table 16: New order!");
        card.setContent("One tiramisu");
        response.setCard(card);
        return response;
    }

    private SpeechletResponse startPayment(final Intent intent, final Session session) {
        String speechText = "Your total bill amount is $25.39. How many payments would you like to make?";
        String repromptText = "How many payments would you like to make?";
        return getSpeechletResponse(speechText, repromptText, true);
    }

    private SpeechletResponse doFirstPayment(final Intent intent, final Session session) {
        String speechText = "Sure. Please swipe your credit cards on the reader on the table.";
        String repromptText = "Please swipe your card";
        session.setAttribute("payment", "progress");
        return getSpeechletResponse(speechText, repromptText, true);
    }

    private SpeechletResponse doSecondPayment(final Intent intent, final Session session) {
        String speechText = "Thank you. Please enter the next card.";
        String repromptText = "Please swipe the next card";
        session.setAttribute("payment", "done");
        SpeechletResponse response = getSpeechletResponse(speechText, repromptText, true);
        SimpleCard card = new SimpleCard();
        card.setTitle("Table 16: Payment received (1 of 2)!");
        card.setContent("$12.69 of $25.39");
        response.setCard(card);
        return response;
    }

    private SpeechletResponse goodbyeMessage(final Intent intent, final Session session) {
        String speechText = "Thank you for dining with us! I hope you had a great time! Do come back soon!";
        SpeechletResponse response = getSpeechletResponse(speechText, speechText, false);
        SimpleCard card = new SimpleCard();
        card.setTitle("Table 16: Payment received (2 of 2)!");
        card.setContent("$25.39 of $25.39");
        response.setCard(card);
        return response;
    }

    public static SpeechletResponse getSpeechletResponse(String speechText, String repromptText, boolean isAskResponse) {

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        if (isAskResponse) {
            PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
            repromptSpeech.setText(repromptText);
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptSpeech);

            return SpeechletResponse.newAskResponse(speech, reprompt);

        } else {
            return SpeechletResponse.newTellResponse(speech);
        }
    }
}

