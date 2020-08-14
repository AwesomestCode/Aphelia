package to.us.awesomest.aphelia.module.chathandlers;

import net.dv8tion.jda.api.entities.Message;
import org.slf4j.LoggerFactory;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import to.us.awesomest.aphelia.comlink.Satellite;

public class MCChannelHandler implements ChatHandler {
    private static MCChannelHandler instance;
    private MCChannelHandler(){}

    public static MCChannelHandler getInstance(){
        if(instance == null) instance = new MCChannelHandler();
        return instance;
    }
    @Override
    public boolean adminException() {
        return false;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void run(Message message) {
        if(!Satellite.hasOutputStream(message.getChannel().getId())) {
            LoggerFactory.getLogger("MCChannelHandler").debug("Skipped message \"" + message + "\" as no matching client was found.");
            return;
        }
        Satellite.passChatMessage(message.getChannel().getId(), message.getGuild().getMember(message.getAuthor()).getEffectiveName(), message.getContentRaw());
        LoggerFactory.getLogger("MCChannelHandler").debug("Sent message \"" + message + "\" to Satellite.");
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
