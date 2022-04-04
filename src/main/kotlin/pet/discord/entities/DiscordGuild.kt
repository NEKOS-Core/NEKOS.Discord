package pet.nekos.discord.entities

import pet.nekos.api.guild.Guild
import pet.nekos.api.channel.Channel

import net.dv8tion.jda.api.entities.Guild as JDAGuild
import net.dv8tion.jda.api.entities.GuildChannel as JDAGuildChannel

class DiscordGuild(
    name: String,
    var _jdaguild: JDAGuild
) : Guild(name) {
    override fun getChannels(): Array<Channel> {
        var channels = mutableListOf<Channel>()
        for (gc in _jdaguild.getChannels(true)) {
            channels.add(DiscordChannel(gc.getName(), this, gc))
        }
        return channels.toTypedArray()
    }
}