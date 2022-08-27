package pet.nekos.discord.entities

import net.dv8tion.jda.api.entities.User as JDAUser
import net.dv8tion.jda.api.entities.Member as JDAMember
import net.dv8tion.jda.api.entities.Guild as JDAGuild

import pet.nekos.api.entities.*

import pet.nekos.discord.Discord

import java.io.File

class DiscordUser (
    name: String,
    nickname: String = name,
    hash: String,
    guild: Guild?,
    service: Discord,
    var _jdauser: JDAUser,
    var _jdamember: JDAMember?,
) : User(name, nickname, hash, guild, service){

    /**
     * Constructor to create a DiscordUser from a JDA User
     * User can not be null.
     *
     * @property user JDA User to create the DiscordUser with
     */
    constructor(user: JDAUser) : this(
        user.getName(),
        user.getName(),
        User.generateHash(user.getId()),
        null,
        Discord(),
        user,
        null) { }

    /**
     * Constructor to create a DiscordUser from a JDA Member
     * Member can not be null.
     *
     * @property user JDA Member to create the DiscordUser with
     */
    constructor(member: JDAMember) : this(
        member.getUser().getName(), 
        member.getEffectiveName(),
        User.generateHash(member.getId()), 
        DiscordGuild(member.getGuild()),
        Discord(),
        member.getUser(),
        member) { }

    /**
     * Constructor to use when it isn't certain if a member is present. Will fill in member properties if possible. 
     * Member can be null.
     * 
     * @property user JDA User to create the DiscordUser with
     * @property user JDA Member to create the DiscordUser with
     */
    constructor(user: JDAUser, member: JDAMember?) : this(
        user.getName(),
        if (member == null) user.getName() else member.getEffectiveName(),
        User.generateHash(user.getId()),
        if (member == null) null else DiscordGuild(member.getGuild()),
        Discord(),
        user,
        member) { }

    override fun sendMessage(content: String, vararg attachments: File ): Boolean {
        _jdauser.openPrivateChannel().queue { channel -> run {
            var reply = channel.sendMessage(content)

            for (f in attachments) {
                reply.addFile(f)
            }
            reply.queue()
        }}

        return true

    }

}