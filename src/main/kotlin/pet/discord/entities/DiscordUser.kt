package pet.nekos.discord.entities

import net.dv8tion.jda.api.entities.User as JDAUser

import pet.nekos.api.user.User

import pet.nekos.discord.Discord

class DiscordUser (
    name: String,
    nickname: String = name,
    hash: String,
    service: Discord,
    _jdauser: JDAUser
) : User(name, nickname, hash, service){ 
    
    constructor(user: JDAUser) : this(
        user.getName(), 
        user.getName(), 
        "TEMPORARY",
        Discord(),
        user) { }

}