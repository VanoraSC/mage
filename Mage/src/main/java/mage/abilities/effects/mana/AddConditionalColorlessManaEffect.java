/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mage.abilities.effects.mana;

import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.effects.common.ManaEffect;
import mage.abilities.mana.builder.ConditionalManaBuilder;
import mage.game.Game;
import mage.players.Player;

/**
 *
 * @author LevelX2
 */
public class AddConditionalColorlessManaEffect extends ManaEffect {

    private final int amount;
    private final ConditionalManaBuilder manaBuilder;

    public AddConditionalColorlessManaEffect(int amount, ConditionalManaBuilder manaBuilder) {
        super();
        this.amount = amount;
        this.manaBuilder = manaBuilder;

        staticText = "Add " + String.format(String.format("%%%ds", amount), " ").replace(" ", "{C}")
                + ". " + manaBuilder.getRule();
    }

    public AddConditionalColorlessManaEffect(final AddConditionalColorlessManaEffect effect) {
        super(effect);
        this.amount = effect.amount;
        this.manaBuilder = effect.manaBuilder;
    }

    @Override
    public AddConditionalColorlessManaEffect copy() {
        return new AddConditionalColorlessManaEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            controller.getManaPool().addMana(getMana(game, source), game, source);
            return true;
        }
        return false;
    }

    @Override
    public Mana produceMana(boolean netMana, Game game, Ability source) {
        return manaBuilder.setMana(Mana.ColorlessMana(amount), source, game).build();
    }

    public Mana getMana() {
        return Mana.ColorlessMana(amount);
    }
}