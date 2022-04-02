package pet.nekos.discord

import pet.nekos.api.event.message.MessageEvent

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class Listener : ListenerAdapter() {
    override fun onMessageReceived(e: MessageReceivedEvent) {
        Discord().getServer().serverManager.fireEvent(MessageEvent(e.message.contentRaw))
    }
}