package pet.nekos.discord

import pet.nekos.api.event.message.MessageEvent
import pet.nekos.api.message.Message

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

import pet.nekos.discord.entities.DiscordMessage

import java.util.Date

class Listener : ListenerAdapter() {
    override fun onMessageReceived(e: MessageReceivedEvent) {
        Discord().getServer().serverManager.fireEvent(MessageEvent(DiscordMessage(e.message), Date()))
    }
}