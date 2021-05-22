package by.minilooth.telegrambot.bot.state;

import by.minilooth.telegrambot.bot.api.BotContext;
import by.minilooth.telegrambot.bot.api.BotState;
import by.minilooth.telegrambot.exception.ClientBotStateException;
import by.minilooth.telegrambot.model.Client;

public enum ClientBotState implements BotState<BotContext<Client>> {
    START(true) {
        @Override
        public void enter(BotContext<Client> botContext) throws ClientBotStateException {
            // throw new ClientBotStateException("adasd", this);
        }

        @Override
        public void handleText(BotContext<Client> botContext) {

        }

        @Override
        public ClientBotState nextState() {
            return MAIN_MENU;
        }

        @Override
        public ClientBotState rootState() {
            return START;
        }

    },

    MAIN_MENU(true) {
        private ClientBotState nextState = null;

        @Override
        public void enter(BotContext<Client> botContext) {

        }

        @Override
        public void handleText(BotContext<Client> botContext) throws ClientBotStateException {
            // throw new ClientBotStateException("adasd", this);
        }

        @Override
        public ClientBotState nextState() {
            return ENTER_FIRSTNAME;
        }

        @Override
        public ClientBotState rootState() {
            return MAIN_MENU;
        }
    },
    
    ENTER_FIRSTNAME(true) {
        private ClientBotState nextState = null;

        @Override
        public void enter(BotContext<Client> botContext) {

        }

        @Override
        public void handleText(BotContext<Client> botContext) {

        }

        @Override
        public ClientBotState nextState() {
            return ENTER_SURNAME;
        }

        @Override
        public ClientBotState rootState() {
            return MAIN_MENU;
        }
    },

    ENTER_SURNAME(true) {
        private ClientBotState nextState = null;

        @Override
        public void enter(BotContext<Client> botContext) {

        }

        @Override
        public void handleText(BotContext<Client> botContext) {

        }

        @Override
        public ClientBotState nextState() {
            return nextState;
        }

        @Override
        public ClientBotState rootState() {
            return MAIN_MENU;
        }
    },

    ENTER_PATRONYMIC(true) {
        private ClientBotState nextState = null;

        @Override
        public void enter(BotContext<Client> botContext) {

        }

        @Override
        public void handleText(BotContext<Client> botContext) {

        }

        @Override
        public ClientBotState nextState() {
            return nextState;
        }

        @Override
        public ClientBotState rootState() {
            return MAIN_MENU;
        }
    };

    private final Boolean isInputNeeded;

    ClientBotState(Boolean isInputNeeded) {
        this.isInputNeeded = isInputNeeded;
    }

    public Boolean getIsInputNeeded() {
        return isInputNeeded;
    }

    public static ClientBotState getInitialState() {
        return START;
    }

    @Override
    public abstract void enter(BotContext<Client> botContext);

    @Override
    public abstract ClientBotState nextState();

    @Override
    public abstract ClientBotState rootState();
    
}
