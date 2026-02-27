using UnityEngine;

public class BattleManager : MonoBehaviour
{
    public int vidaJugador = 100;
    public int vidaEnemigo = 100;

    public void AtacarEnemigo(int daño)
    {
        vidaEnemigo -= daño;
        Debug.Log($"Enemigo recibe {daño} de daño. Vida restante: {vidaEnemigo}");
    }

    public void EnemigoAtaca()
    {
        int daño = Random.Range(5, 10);
        vidaJugador -= daño;
        Debug.Log($"El enemigo ataca y hace {daño} de daño. Vida del jugador: {vidaJugador}");
    }
}
