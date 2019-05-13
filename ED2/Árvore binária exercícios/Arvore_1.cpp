#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

typedef struct arv
{
    int info;
    arv *sae;
    arv *sad;

}arvore;

arvore* cria_arvore()
{
    return NULL;
}

int testa_vazia(arvore *t)
{
    return t == NULL;
}

int mostra_folha(arvore *t, int &cont)
{
    if(!testa_vazia(t))
    {
        if((t->sae == NULL) && (t->sad == NULL))
        {
            cont++;
            return cont;
        }
        mostra_folha(t->sae, cont);
        mostra_folha(t->sad, cont);
    }
    return cont;
}

int mostra_maior(arvore *t, int valor, int &cont)
{
    if(!testa_vazia(t))
    {
        if(t->info > valor)
        {
            cont++;
            //return cont;
        }
        mostra_maior(t->sae, valor, cont);
        mostra_maior(t->sad, valor, cont);
    }
    return cont;
}

int mostra_filho_unico(arvore *t, int &cont)
{
    if(!testa_vazia(t))
    {
        //if((t->sae == NULL && t->sad != NULL) || (t->sae != NULL && t->sad == NULL))
        if(t->sae == NULL ^ t->sad == NULL)
        {
            cont++;
        }
        mostra_filho_unico(t->sae, cont);
        mostra_filho_unico(t->sad, cont);
    }
    return cont;
}

void mostra_preordem(arvore *t)
{
    cout << "<";
    if(!testa_vazia(t))
    {
        cout << t->info;
        mostra_preordem(t->sae);
        mostra_preordem(t->sad);
    }
    cout << ">";
}

void mostra_inordem(arvore *t){
  cout << "<";
  if(!testa_vazia(t))
  {
     mostra_inordem(t->sae);
     cout << t->info;
     mostra_inordem(t->sad);
  }
  cout << ">";
}

void mostra_posordem(arvore *t){
  cout << "<";
  if(!testa_vazia(t))
  {
     mostra_posordem(t->sae);
     mostra_posordem(t->sad);
     cout << t->info;
  }
  cout << ">";
}

void inserir(arvore **t, int v)
{
    //arvore esta vazia
    if(*t == NULL)
    {
        *t = new arvore;
        (*t)->sae = NULL;
        (*t)->sad = NULL;
        (*t)->info = v;
    }
    else
    {
        if(v < (*t)->info)
            inserir(&(*t)->sae, v);
        else
            inserir(&(*t)->sad, v);
    }
}

bool consulta(arvore *t, int v)
{
    if(testa_vazia(t))
        return 0;
    return t->info == v || consulta(t->sae, v) || consulta(t->sad,v);
}

arvore* remover(arvore **t, int v)
{
    if(v < (*t)->info)
        remover(&(*t)->sae, v);
    else if(v > (*t)->info)
        remover(&(*t)->sad, v);
    else
    {
        //achou o elemento a ser removido
        arvore *aux = *t;
        if(((*t)->sae == NULL) && ((*t)->sad == NULL))
        {
            //é um nó folha
            delete(aux);
            (*t) = NULL;
        }
        else if((*t)->sae == NULL)
        {
            //só tem filho a direita
            (*t) = (*t)->sad;
            aux->sad = NULL;
            delete(aux);
            aux = NULL;
        }
        else if ((*t)->sad == NULL)
        {
            //só tem filho a esquerda
            (*t) = (*t)->sae;
            aux->sae = NULL;
            delete(aux);
            aux = NULL;
        }
        else
        {
            aux = (*t)->sae;
            while(aux->sad != NULL)
            {
                aux = aux->sad;
            }
            (*t)->info = aux->info;
            aux->info = v;
            (*t)->sae = remover(&(*t)->sae, v);
        }
    }
    return *t;
}

main()
{
    int menu = -1, num, cont = 0;
    arvore *a = cria_arvore();
    do
    {
        system("cls");
        cout << "MENU DE OPCOES" << endl;
        cout << " 0 - Sair" << endl;
        cout << " 1 - Incluir" << endl;
        cout << " 2 - Mostrar" << endl;
        cout << " 3 - Consultar" << endl;
        cout << " 4 - Excluir" << endl;
        cout << " 5 - Conta folhas" << endl;
        cout << " 6 - Conta maiores" << endl;
        cout << " 7 - Conta nos com filho unico" << endl;
        cout << "Sua escolha: ";
        cin >> menu;
        fflush(stdin);
        switch(menu)
        {
           case 0:
              system("cls");
              cout << "Programa finalizado";
              getchar();
              break;
           case 1:
              system("cls");
              cout << "Informe o valor a ser inserido: ";
              cin >> num;
              fflush(stdin);
              inserir(&a, num);
              cout << "VALOR INSERIDO COM SUCESSO!";
              getchar();
              break;
           case 2:
              system("cls");
              if(testa_vazia(a))
              {
                  cout << "ARVORE VAZIA!";
              }
              else
              {
                  cout << "ELEMENTOS EM PRE-ORDEM: "<< endl;
                  mostra_preordem(a);
                  cout << "\n\nELEMENTOS EM ORDEM SIMETRICA: "<< endl;
                  mostra_inordem(a);
                  cout << "\n\nELEMENTOS EM POS-ORDEM: "<< endl;
                  mostra_posordem(a);
              }

              getchar();
              break;
           case 3:
              system("cls");
              if(testa_vazia(a))
              {
                  cout << "ARVORE VAZIA!";
              }
              else
              {
                  cout << "Informe o valor a ser consultado: ";
                  cin >> num;
                  fflush(stdin);
                  if(consulta(a, num))
                     cout << "O valor pertence a arvore.";
                  else
                     cout << "O valor nao pertence a arvore.";
              }
              getchar();
              break;
           case 4:
              system("cls");
              if(testa_vazia(a))
              {
                  cout << "ARVORE VAZIA!";
              }
              else
              {
                  cout << "Informe o valor a ser removido: ";
                  cin >> num;
                  fflush(stdin);
                  if(a->sae == NULL && a->sad == NULL)
                  {
                      delete (a);
                      a = NULL;
                  }
                  else
                      remover(&a, num);
                  cout << "VALOR REMOVIDO COM SUCESSO.";
              }
              getchar();
              break;

              case 5:
                  system("cls");
                  if(testa_vazia(a))
                  {
                      cout << "ARVORE VAZIA!";
                  }
                  else
                  {
                      cout << "HA " << mostra_folha(a, cont) << " ELEMENTO(S) FOLHA" << endl;

                  }

                  getchar();
                  break;

              case 6:
                  system("cls");
                  if(testa_vazia(a))
                  {
                      cout << "ARVORE VAZIA!";
                  }
                  else
                  {
                      cout << "Informe o valor a ser consultado: ";
                      cin >> num;
                      fflush(stdin);
                      cout << "HA " << mostra_maior(a, num, cont) << " ELEMENTO(S) MAIOR(ES) QUE " << num << endl;
                      cont = 0;
                  }
                  getchar();
                  break;

              case 7:
                  system("cls");
                  if(testa_vazia(a))
                  {
                      cout << "ARVORE VAZIA!";
                  }
                  else
                  {
                      cout << "HA " << mostra_filho_unico(a, cont) << " NO(S) COM UM UNICO FILHO" << endl;
                      cont = 0;
                  }

                  getchar();
                  break;

        };

    }while(menu != 0);

}
