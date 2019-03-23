# tegimen

*tegimen*, Latin noun: shell, husk, armor

Black magic:

```
(def dirs (sh ls -a))

;; Better yet:
(defn list-everything
  [flags]
  (sh ls ~@(map (partial str "-")
                flags))))
```
What could possibly go wrong?
