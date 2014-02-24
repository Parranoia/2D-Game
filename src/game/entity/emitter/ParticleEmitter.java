package game.entity.emitter;

import game.entity.particle.Particle;
import game.level.Level;

public class ParticleEmitter extends Emitter
{
    private int life;

    public ParticleEmitter(int x, int y, int life, int amount, Level level)
    {
        super(x, y, Emitter.Type.PARTICLE, amount, level);

        this.life = life;

        for (int i = 0; i < amount; i++)
            level.add(new Particle(x, y, life));
    }
}
