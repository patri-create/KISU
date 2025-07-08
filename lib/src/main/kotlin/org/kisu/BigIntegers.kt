package org.kisu

import java.math.BigInteger

val BigInteger.zero: Boolean
    get() = this.compareTo(BigInteger.ZERO) == 0