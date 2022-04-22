package pet.nekos.discord.entities

import pet.nekos.api.guild.Guild
import pet.nekos.api.channel.GuildChannel

import net.dv8tion.jda.api.entities.Guild as JDAGuild
import net.dv8tion.jda.api.entities.GuildChannel as JDAGuildChannel

import pet.nekos.discord.entities.DiscordGuildChannel
import pet.nekos.discord.Discord

class DiscordGuild(
    name: String,
    service: Discord,
    var _jdaguild: JDAGuild
) : Guild(name, service) {

    constructor(guild: JDAGuild) : this(
        guild.getName(),
        Discord(),
        guild) { }

    override fun getChannels(): Array<GuildChannel> {
        var channels = mutableListOf<GuildChannel>()
        for (gc in _jdaguild.getChannels(true)) {
            channels.add(DiscordGuildChannel(gc))
        }
        return channels.toTypedArray()
    }
}