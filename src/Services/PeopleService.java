package Services;

import Models.Person;
import Models.Requisicao;
import Models.Resposta;

public class PeopleService {
	
	public Resposta calcular(Requisicao requisicao) {
        int op1 = requisicao.getOperando1();
        int op2 = requisicao.getOperando2();
        switch (requisicao.getOperador()) {
            case '+':
                return new Resposta(op1 + op2, 1);
            case '-':
                return new Resposta(op1 - op2, 1);
            case '*':
                return new Resposta(op1 * op2, 1);
            case '/':
                if (op2 == 0) {
                    return new Resposta(0, 0);
                } else {
                    return new Resposta(op1 / op2, 1);
                }
            default:
                return new Resposta(0, 0);
        }
    }
	
	public void insert(Person person) {}
	public void update(Person person) {}
	public void delete(Person person) {}
	public Person get() {
		return new Person(null, 0, null, null, null);
	}
}
