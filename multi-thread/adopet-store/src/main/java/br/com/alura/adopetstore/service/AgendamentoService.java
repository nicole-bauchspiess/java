package br.com.alura.adopetstore.service;

import br.com.alura.adopetstore.email.EmailRealatorioGerado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AgendamentoService {

    @Autowired
    RelatorioService relatorioService;

    @Autowired
    EmailRealatorioGerado enviador;


    @Scheduled(cron = "0 0 2 * * *")
    public void envioDeEmailsAgendado() {
        var estoqueZerado = relatorioService.infoEstoque();
        var faturamento = relatorioService.faturamentoObtido();

        //paralaliza a busca do infoestoque e faturamento por meio do completableFuture
        //força que espere as duas threas terminarem para enviar
        //A classe Future permite que a gente recupere o retorno de uma threads, o async não deixa ter retorno
        CompletableFuture.allOf(estoqueZerado, faturamento).join();

        try {
            enviador.enviar(estoqueZerado.get(), faturamento.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
