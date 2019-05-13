Q#include<iostream>
#include<cstdlib>
using namespace std;
struct arv
{
    int info;
    char cor;
    arv *sae,*sad,*par;
};
typedef arv *arvptr;
arvptr raiz,par;
class aj
{
    int i,j;
public:
    void add(int x,arvptr &);

    void set(arvptr );

    arvptr lr(arvptr,arvptr );
    arvptr rr(arvptr,arvptr );

    void inorder(arvptr );
};
void aj::set(arvptr n)
{
    if(n->par==NULL)
        n->cor='b';
    else if(n->par->cor=='b')
        return;
    else
    {
        arvptr u,d,p=n->par;
        d=n->par->par;
        if(p==d->sae)
            u=d->sad;
        else
            u=d->sae;
        if(u!=NULL&&u->cor=='r')
        {
            p->cor='b';
            u->cor='b';
            d->cor='r';
            set(d);
        }
        else
        {
            if(n==p->sad&&p==d->sae)
            {
                p=lr(p,n);
                n=n->sae;
            }
            else if(n==p->sae&&p==d->sad)
            {
                p=rr(p,n);
                n=n->sad;
            }
            p=n->par;//not required
            p->cor='b';
            d->cor='r';
            if(n==p->sae)
                d=rr(d,d->sae);
            else
                d=lr(d,d->sad);
        }
    }
}
void aj::inorder(arvptr p)
{
    if(p!=NULL)
    {
        inorder(p->sae);
        cout<<endl<<p->info<<" "<<p->cor;
        inorder(p->sad);
    }
}
arvptr aj::rr(arvptr p,arvptr q)
{
    arvptr r=p;
    p->sae=q->sad;
    if(q->sad!=NULL)
        q->sad->par=p;
    q->sad=p;
    q->par=p->par;
    p->par=q;
    if(q->par!=NULL)
    {
        if(q->info<q->par->info)
            q->par->sae=q;
        else
            q->par->sad=q;
    }
    if(r==raiz)
        raiz=q;
    return q;
}
arvptr aj::lr(arvptr p,arvptr q)
{
    arvptr r=p;
    p->sad=q->sae;
    if(q->sae!=NULL)
        q->sae->par=p;
    q->sae=p;
    q->par=p->par;
    p->par=q;
    if(q->par!=NULL)
    {
        if(q->info<q->par->info)
            q->par->sae=q;
        else
            q->par->sad=q;
    }
    if(r==raiz)
        raiz=q;
    return q;
}
void aj::add(int x,arvptr &n)
{
    if(n==NULL)
    {
        n=new arv;
        n->info=x;
        n->sae=NULL;
        n->sad=NULL;
        n->cor='r';
        n->par=par;
        set(n);
    }
    else if(x<n->info)
    {
        par=n;
        add(x,n->sae);
    }
    else if(x>n->info)
    {
        par=n;
        add(x,n->sad);
    }
    else
        cout<<"\nData exist";
}
int main()
{
    raiz=par=NULL;
    aj a;
    a.add(20,raiz);
    a.add(10,raiz);
    a.add(16,raiz);
    a.add(17,raiz);
    a.add(19,raiz);
    a.inorder(raiz);
    system("pause");
    return 0;
}
