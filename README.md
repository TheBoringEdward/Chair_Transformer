# Structure Rework

---

This branch serves to re-do the entire structure of the rendered, as to prepare it for more useful projects.

This will be done as follows:

- Object
  - Models
    - Transformation
      - Realm
        - Eye
  - Brains
    - Events
      - Models
      - HUD
        - Eye

> ## Object
> The object can be anything that exists in a "physical" sense, be it visible or invisible.

> ## Models
> Most objects have their own models, either to serve as a visual indicator, or for collision-checks.

> ## Transformation
> Transforms models, for example, making them invisible, turning them, scaling them, and placing them on a specific coordinate.

> ## Realm
> Takes all transformed models, and combines them together in a single *realm* / world, preparing them for rendering.

> ## Eye
> The eye is the viewport, i.e. the camera, which is placed within the realm with its own transformation. It can be modified through certain events.

> ## Brains
> Almost all objects have their own set of behaviours. These behaviours can call certain events, e.g. moving a model.
> Objects can however also store metadata, 
> Behaviours are triggered through specific events, such as timers, collisions, sight, etc...

> ## HUD
> The HUD is updated through certain events and is projected onto the eye