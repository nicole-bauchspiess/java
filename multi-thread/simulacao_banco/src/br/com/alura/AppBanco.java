package br.com.alura;

import java.math.BigDecimal;

public class AppBanco {
    public static void main(String[] args) {
        var cliente = new Cliente("joao");
        var conta = new Conta(cliente, new BigDecimal("150.0"));

        OperacaoSaque operacao = new OperacaoSaque(conta, new BigDecimal("150"));

        // Cria uma thread na memória da jvm
        //  Ao passar um runable informa que quer paralelizar as threads.
        //  Classe OperacaoSaque precisa implementar Runnable / run

        // paralelismo:
        Thread saqueJoao = new Thread(operacao);
        Thread saqueMaria = new Thread(operacao);

        saqueJoao.start();
        saqueMaria.start();

        try {
            // A thread main aguarda a execução das threads criadas antes de seguir para as proximas instruções
            saqueJoao.join();
            saqueMaria.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName());
        System.out.println("Saldo final: " + conta.getSaldo());
    }
}
