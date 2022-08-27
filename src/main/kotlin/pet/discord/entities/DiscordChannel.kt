package pet.nekos.discord.entities

import pet.nekos.api.entities.*

import net.dv8tion.jda.api.entities.Channel as JDAChannel
import net.dv8tion.jda.api.entities.User as JDAUser
import net.dv8tion.jda.api.entities.GuildChannel
import net.dv8tion.jda.api.entities.PrivateChannel
import net.dv8tion.jda.api.entities.IMemberContainer

import pet.nekos.discord.Discord

class DiscordChannel(
    name: String,
    service: Discord,
    guild: DiscordGuild?,
    var _jdachannel: JDAChannel
) : Channel(name, service, guild) {

    constructor(channel: JDAChannel) : this(
        channel.getName(),
        Discord(),
        if (channel is GuildChannel) DiscordGuild(channel.getGuild()) else null,
        channel) { }

    override fun getMembers(): Array<User> {
        var users = mutableListOf<User>()
        if (_jdachannel is PrivateChannel) {
            users.add((DiscordUser((_jdachannel as PrivateChannel).getUser() as JDAUser)))
            users.add(DiscordUser(_jdachannel.getJDA().getSelfUser()))
        } else if (_jdachannel is IMemberContainer) {
            for (u in (_jdachannel as IMemberContainer).getMembers()) {
                users.add(DiscordUser(u as JDAUser))
            }
        }
        return users.toTypedArray()
    }
}