package pet.nekos.discord

import pet.nekos.api.event.message.MessageEvent
import pet.nekos.api.message.Message

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

import pet.nekos.discord.entities.DiscordUser
import pet.nekos.discord.entities.DiscordChannel
import pet.nekos.discord.entities.DiscordMessage
import pet.nekos.discord.entities.DiscordGuild

import java.util.Date

class Listener : ListenerAdapter() {
    override fun onMessageReceived(e: MessageReceivedEvent) {
        var user = DiscordUser(e.author.getName(), e.member?.getEffectiveName() as String, "TEMPORARY")
        var guild = DiscordGuild(e.message.guild.name, e.message.guild)
        var channel = DiscordChannel(e.message.channel.name, guild, e.message.guildChannel)
        var message = DiscordMessage(e.message.contentRaw, user, channel, e.message, Discord())
        Discord().getServer().serverManager.fireEvent(MessageEvent(message, Date()))
    }
}