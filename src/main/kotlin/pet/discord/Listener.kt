package pet.nekos.discord

import pet.nekos.api.event.message.MessageEvent
import pet.nekos.api.entities.Entity

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

import pet.nekos.discord.entities.*

import java.util.Date

class Listener : ListenerAdapter() {
    override fun onMessageReceived(e: MessageReceivedEvent) {
        var entities = mutableSetOf<Entity>(
            DiscordMessage(e.message), 
            DiscordUser(e.author, e.member), 
            DiscordChannel(e.channel), 
        )
        if (e.isFromGuild) {
            entities.add(DiscordGuild(e.guild))
            entities.add(DiscordGuildChannel(e.guildChannel))
        }

        Discord().getServer().serverManager.fireEvent(MessageEvent(), *entities.toTypedArray()) 
    }
}