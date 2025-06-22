package org.kisu.test.fakes

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.InBase
import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.Symbol

class TestPrefix(override val power: Int) : Prefix, InBase by InBase.Decimal, Symbol by Representation("T")
