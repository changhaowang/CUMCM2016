function q=calQ(w)
w0=[0.196; 0.191; 0.203; 0.210; 0.2];
q=(w0.*w)./sum(w0.*w);