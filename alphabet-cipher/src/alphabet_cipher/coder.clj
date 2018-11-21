(ns alphabet-cipher.coder)

(def columns {:a 0 :b 1 :c 2 :d 3 :e 4 :f 5 :g 6 :h 7 :i 8 :j 9 :k 10 :l 11
              :m 12 :n 13 :o 14 :p 15 :q 16 :r 17 :s 18 :t 19 :u 20 :v 21 :w 22
              :x 23 :y 24 :z 25})

(def substitution-chart
  {:a "abcdefghijklmnopqrstuvwxyz"
   :b "bcdefghijklmnopqrstuvwxyza"
   :c "cdefghijklmnopqrstuvwxyzab"
   :d "defghijklmnopqrstuvwxyzabc"
   :e "efghijklmnopqrstuvwxyzabcd"
   :f "fghijklmnopqrstuvwxyzabcde"
   :g "ghijklmnopqrstuvwxyzabcdef"
   :h "hijklmnopqrstuvwxyzabcdefg"
   :i "ijklmnopqrstuvwxyzabcdefgh"
   :j "jklmnopqrstuvwxyzabcdefghi"
   :k "klmnopqrstuvwxyzabcdefghij"
   :l "lmnopqrstuvwxyzabcdefghijk"
   :m "mnopqrstuvwxyzabcdefghijkl"
   :n "nopqrstuvwxyzabcdefghijklm"
   :o "opqrstuvwxyzabcdefghijklmn"
   :p "pqrstuvwxyzabcdefghijklmno"
   :q "qrstuvwxyzabcdefghijklmnop"
   :r "rstuvwxyzabcdefghijklmnopq"
   :s "stuvwxyzabcdefghijklmnopqr"
   :t "tuvwxyzabcdefghijklmnopqrs"
   :u "uvwxyzabcdefghijklmnopqrst"
   :v "vwxyzabcdefghijklmnopqrstu"
   :w "wxyzabcdefghijklmnopqrstuv"
   :x "xyzabcdefghijklmnopqrstuvw"
   :y "yzabcdefghijklmnopqrstuvwx"
   :z "zabcdefghijklmnopqrstuvwxy"})

(defn char-to-key [c]
  (keyword (str c)))


;; Returns the keyword repeated til a given length
;; Updated solution based on feedback in #beginners
(defn expand-keyword [key message]
  (let [key-length (count key)
        message-length (count message)]
    (cond (> key-length message-length)
          (subs key 0 message-length)
          (= key-length message-length)
          key
          (< key-length message-length)
          (->> (cycle key)
               (take message-length)
               (apply str)))))


(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")

