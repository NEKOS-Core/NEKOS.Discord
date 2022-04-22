package pet.nekos.discord.entities

import net.dv8tion.jda.api.entities.User as JDAUser
import net.dv8tion.jda.api.entities.Member as JDAMember

import pet.nekos.api.user.User

class DiscordUser (
    name: String,
    nickname: String = name,
    hash: String,
    _jdauser: JDAUser
) : User(name, nickname, hash){ 
    
    constructor(user: JDAUser) : this(
        user.getName(), 
        user.getName(), 
        "TEMPORARY",
        user) { }
    
    constructor(member: JDAMember) : this(
        member.getUser().getName(), 
        member.getEffectiveName() 
        "TEMPORARY",
        member.getUser()) { }

}