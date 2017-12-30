function [r,w,q] = comput(a)
  r=calR(a);
  f=calF(r);
  h=calH(f);
  w=calW(h);
  q=calQ(w);
end

