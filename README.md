# PermissionLevels-Fabric
This fabric mod adds a command, `/xop`, and replaces the vanilla `/op` with it. It looks like this:

`/op username 2`

This command will make the player `username` a level two op. It also has an optional parameter:

`/op username 2 false`

This will make username a level two op, but does not let them bypass the player limit. The default value is true.

If you need this mod for an older minecraft version feel free to ask for a backport, it should be pretty straightforward.

# License, modpack permissions, acknowledgements
This mod is licensed AGPL; the short version that any publicly distributed fork must be licensed AGPL, and if you host a public server with a fork of this mod, then that is considered distributing it.

You are allowed to use this mod in your modpacks under all circumstances, there is no need to ask for permission or notify me.

See also the [forge mod](https://github.com/Crimix/PermissionLevels) that inspired this mod. Note that this mod uses no code from that mod, as it has no open-source license.
